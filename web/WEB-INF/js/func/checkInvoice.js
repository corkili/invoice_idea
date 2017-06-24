function checkQuantity(input) {
    var value = input.value;
    if (value.indexOf('.') >= 0 || isNaN(Number(value)) || Number(value) <= 0) {
        alert("数量：请输入大于0整数");
        input.focus();
    }
}

function checkMoney(input) {
    var value = input.value;
    if (isNaN(Number(value)) || Number(value) < 0) {
        alert("金额：请输入正数");
        input.focus();
    }
}

function checkRate(input) {
    var value = input.value;
    if (value.indexOf('.') >= 0 || isNaN(Number(value)) || Number(value) < 0 || Number(value) > 1) {
        alert("数量：请输入区间[0-1]内的小数");
        input.focus();
    }
}
