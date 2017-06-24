package org.invoice.service;

import org.apache.log4j.Logger;
import org.invoice.dao.UserDao;
import org.invoice.model.Authority;
import org.invoice.model.User;
import org.invoice.security.HashUtil;
import org.invoice.session.SessionContext;
import org.invoice.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供用户相关服务的实体类
 * Created by 李浩然 on 2017/4/8.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private InvoiceService invoiceService;

    private Map<Integer, User> loginUsers;

    private Map<Integer, List<User>> userLists;

    private Logger logger = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
        loginUsers = new HashMap<>();
        userLists = new HashMap<>();
    }

    @Override
    public boolean register(User user, Errors errors) {
        boolean hasError = true;
        if (!validateUserInfo(user, errors))
            hasError = false;

        if (userDao.findUserByUserName(user.getUsername()) != null) {
            if (errors != null)
                errors.rejectValue("username", "username.exists", "用户名已存在");
            hasError = false;
        }
        if (user.getPhone().length() != 11
                || !Pattern.compile("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2,5-9])|(177))\\d{8}$")
                .matcher(user.getPhone()).matches()) {
            if (errors != null)
                errors.rejectValue("phone", "phone.error", "无效的手机号");
            hasError = false;
        }
        if (user.getPassword().trim().length() < 8) {
            if (errors != null)
                errors.rejectValue("password", "password.length", "密码长度至少为8位");
            hasError = false;
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            if (errors != null)
                errors.rejectValue("confirmPassword", "password.not_equal", "两次输入的密码不一致");
            hasError = false;
        }
        if (!hasError) {
            return false;
        }
        user.setAuthority(0);
        user.setPassword(HashUtil.generate(user.getPassword()));
        user.setSalt(HashUtil.getSalt(user.getPassword()));
        userDao.addUser(user);
        return true;
    }

    @Override
    public boolean login(User user, Errors errors, HttpSession session) {
        if(StringUtils.isEmpty(user.getUsername())) {
            logger.info("validate: \"username\" is empty!");
            errors.rejectValue("username", "username.required", "用户名不能为空");
            return false;
        }
        if(StringUtils.isEmpty(user.getPassword())) {
            logger.info("validate: \"password\" is empty!");
            errors.rejectValue("password", "password.required", "密码不能为空");
            return false;
        }

        User loginUser = userDao.findUserByUserName(user.getUsername());
        logger.info(loginUser);
        if (loginUser == null || !HashUtil.verify(user.getPassword(), loginUser.getPassword())) {
            errors.rejectValue("password", "password.error", "用户名或密码错误");
            logger.info("用户名或密码错误，登录失败!");
            return false;
        }

        // 添加登录信息
        loginUser.setSalt("#");
        loginUser.setPassword("#");
        loginUsers.remove(loginUser.getUserId());
        userLists.remove(loginUser.getUserId());
        loginUsers.put(loginUser.getUserId(), loginUser);
        // session处理
        session.setAttribute(SessionContext.ATTR_USER_ID, String.valueOf(loginUser.getUserId()));
        sessionHandlerByCacheMap(session);
        return true;
    }

    @Override
    public void logout(int userId, HttpSession session) {
        loginUsers.remove(userId);
        userLists.remove(userId);
        invoiceService.removeInvoiceListByUserId(userId);
        session.invalidate();
    }

    @Override
    public boolean modifyUserInfo(User user, Errors errors) {
        User oldUser = userDao.findUserByUserName(user.getUsername());
        user.setPassword(oldUser.getPassword());
        user.setSalt(oldUser.getSalt());
        if (!validateUserInfo(user, errors)) {
            logger.info("has errors");
            return false;
        }
        userDao.updateUser(user);
        return true;
    }

    @Override
    public boolean modifyUserPassword(int userId, String newPassword, Errors errors) {
        boolean hasError = true;
        User user = userDao.findUserByUserId(String.valueOf(userId));
        user.setPassword(newPassword);
        if (!validateUserInfo(user, errors)) {
            hasError = false;
        }
        user.setPassword(HashUtil.generate(newPassword));
        user.setSalt(HashUtil.getSalt(user.getPassword()));
        userDao.updateUser(user);
        return true;
    }

    @Override
    public List<User> getAllUserList(int userId) {
        List<User> userList = userLists.get(userId);
        if (userList == null) {
            userList = userDao.findAllUser();
            userLists.put(userId, userList);
        }
        return userList;
    }

    @Override
    public User findUserByUserId(int userId) {
        return loginUsers.get(userId);
    }

    @Override
    public User findUserByUserName(String username) {
        for (User user : loginUsers.values()) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    @Override
    public User findUserByUserNameFromDB(String username) {
        return userDao.findUserByUserName(username);
    }

    @Override
    public User findUserByUserNameAndPasswordFromDB(String username, String password) {
        return userDao.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public void addAuthorityOfUser(int authority, User user) {
        user.setAuthority(authority);
    }

    @Override
    public void removeAuthorityOfUser(int authority, User user) {
        user.removeAuthority(authority);
    }

    @Override
    public boolean validateUserLoginInformation(User user) {
        return saveUserIfLoginInfoCorrect(user);
    }

    private boolean saveUserIfLoginInfoCorrect(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if(isLoginInformationCorrect(username, password)) {
            user.setAuthority(Authority.getAllAuthority());
            user.setUserId(0);
            return true;
        }
        return false;
    }

    private boolean isLoginInformationCorrect(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    private boolean validateUserInfo(User user, Errors errors) {
        boolean hasError = true;
        // 空值校验
        Map<String, String> map = user.getNeedValidateUserInfo();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isEmpty(entry.getValue()) || StringUtils.containsWhitespace(entry.getValue())) {
                logger.info("error: " + entry.toString());
                if (errors != null)
                    errors.rejectValue(entry.getKey(), entry.getKey() + ".required", "此项不能为空或包含空格");
                hasError = false;
            }
        }
        if (!hasError)
            return false;
        // 合法性校验
        logger.info(user.getUsername());
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(user.getEmail());
        if (!matcher.matches()) {
            if (errors != null)
                errors.rejectValue("email", "email.error", "无效的邮箱");
            hasError = false;
        }
        if (!hasError)
            return false;
        return true;
    }

    private void sessionHandlerByCacheMap(HttpSession session) {
        synchronized (SessionListener.sessionContext) {
            String userId = session.getAttribute(SessionContext.ATTR_USER_ID).toString();
            HttpSession userSession = (HttpSession) SessionListener.sessionContext
                    .getSessionMap().get(userId);
            if (userSession != null) {
                // 注销在线用户
                userSession.invalidate();
                SessionListener.sessionContext.getSessionMap().remove(userId);
                // 清楚在线用户后，更新map，替换map
                SessionListener.sessionContext.getSessionMap().remove(session.getId());
                SessionListener.sessionContext.getSessionMap().put(userId, session);
            } else {
                // 根据当前sessionId取session对象，更新map
                SessionListener.sessionContext.getSessionMap().put(userId, SessionListener.sessionContext
                        .getSessionMap().get(session.getId()));
                SessionListener.sessionContext.getSessionMap().remove(session.getId());
            }
        }
    }
}
