/**
 * 用户名必须在3位以上
 * @param  {[type]} str [description]
 * @return {[type]}     [description]
 */
export function validUsername(str) {
    return str.trim().length >= 3
}
/**
 * 校验URL
 * @param  {[type]} str [description]
 * @return {[type]}     [description]
 */
export function validateURL(str) {
    var strRegex = "^((https|http|ftp|rtsp|mms)?://)" +
        "(([0-9]{1,3}\.){3}[0-9]{1,3}" +
        "|" +
        "([0-9a-zA-Z_!~*'()-]+\.)*" +
        "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\." +
        "[a-zA-Z]{2,6})" +
        "(:[0-9]{1,4})?" +
        "((/?)|" +
        "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    var re = new RegExp(strRegex);
    if (re.test(str)) {
        return (true);
    } else {
        return (false);
    }
}
/**
 * 密码格式验证，8-16位字符，必须同时含有大小写字母、数字
 * @param  {[type]} password [description]
 * @return {[type]}          [description]
 */
export function validPassword(password) {
    var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/;
    if (re.test(password)) {
        return true;
    } else {
        return false;
    }
}
/**
 * 邮箱格式验证
 * @param  {[type]} email [description]
 * @return {[type]}       [description]
 */
export function validEmail(email) {
    var str = email;
    var re = /^([a-z0-9]+[_|\_|\.]?)*[a-z0-9]+@([a-z0-9]+[_|\_|\.]?)*[a-z0-9]+\.[a-z]{2,3}$/;
    if (re.test(str)) {
        return true;
    } else {
        return false;
    }
}