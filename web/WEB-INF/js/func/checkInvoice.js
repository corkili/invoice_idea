function resetQuantity(input) {
    var value = input.value;
    if (value.indexOf('.') >= 0 || isNaN(Number(value)) || Number(value) <= 0) {
        input.value = "";
    }
}

function resetMoney(input) {
    var value = input.value;
    if (isNaN(Number(value)) || Number(value) <= 0) {
        input.value = "";
    }
}

function resetRate(input) {
    var value = input.value;
    if (isNaN(Number(value)) || Number(value) <= 0 || Number(value) > 1) {
        input.value = "";
    }
}

function checkQuantity(input) {
    var value = input.value;
    if (value.indexOf('.') >= 0 || isNaN(Number(value)) || Number(value) <= 0) {
        input.value = 1;
        alert("数量：请输入大于0整数");
        input.focus();
    }
}

function checkMoney(input) {
    var value = input.value;
    if (isNaN(Number(value)) || Number(value) < 0) {
        alert("金额：请输入正数");
        input.value = 0;
        input.focus();
    }
}

function checkRate(input) {
    var value = input.value;
    if (isNaN(Number(value)) || Number(value) < 0 || Number(value) > 1) {
        input.value = 0;
        alert("税率：请输入区间[0,1]内的小数");
        input.focus();
    }
}

function checkSubmit(num) {
    var quantities = [];
    var taxRates = [];
    var moneys = [];
    var i;
    for (i = 0; i < num; i++) {
        moneys.push(document.getElementById("unitPrice_" + i).value);
        moneys.push(document.getElementById("amount_" + i).value);
        moneys.push(document.getElementById("taxSum_" + i).value);
        taxRates.push(document.getElementById("taxRate_" + i).value);
        quantities.push(document.getElementById("quantity_" + i).value);
    }
    moneys.push(document.getElementById("totalAmount").value);
    moneys.push(document.getElementById("totalTax").value);
    moneys.push(document.getElementById("total").value);

    var value = "";

    for (i = 0; i < quantities.length; i++) {
        value = quantities[i];
        if (value.indexOf('.') >= 0 || isNaN(Number(value)) || Number(value) <= 0) {
            alert("数量：请输入大于0整数");
            return false;
        }
    }

    for (i = 0; i < moneys.length; i++) {
        value = moneys[i];
        if (isNaN(Number(value)) || Number(value) < 0) {
            alert("金额：请输入正数：" + value);
            return false;
        }
    }

    for (i = 0; i < taxRates.length; i++) {
        value = taxRates[i];
        if (isNaN(Number(value)) || Number(value) < 0 || Number(value) > 1) {
            alert("税率：请输入区间[0-1]内的小数");
            return false;
        }
    }
    return true;
}
