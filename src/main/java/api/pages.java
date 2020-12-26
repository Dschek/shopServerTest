package api;

public class pages {
    private final static String login = "<div style=\"margin-left: 35%;margin-top: 150px;position: absolute;display: table-cell;\"><form name=\"loginform\" id=\"loginform\"><p><label for=\"user_login\">логин</label></p><div><input type=\"text\" name=\"log\" id=\"user_login\" aria-describedby=\"login_error\" class=\"input\" value=\"\" size=\"20\" autocapitalize=\"off\"></div><p class=\"user-pass-wrap\"><label for=\"user_pass\">Пароль</label></p><div class=\"wp-pwd\"><input type=\"password\" name=\"pwd\" id=\"user_pass\" aria-describedby=\"login_error\" class=\"input\" value=\"\" size=\"20\"></div><p class=\"submit\"><input type=\"submit\" name=\"wp-submit\" id=\"wp-submit\" class=\"button button-primary button-large\" style=\" width: -webkit-fill-available; height: 20px;\"></p></form></div>";
    private final static String resultTry = "<div id=\"content\" style=\"margin-left: 35%;margin-top: 150px;position: absolute;display: table-cell;width: 300px;height: 300px;background: limegreen;border-radius: 50%;\">\n" +
            "\n" +
            "    <i style=\"\n" +
            "    font-size: 170px;\n" +
            "    color: red;\n" +
            "    margin: 23%;\n" +
            "\"> ✔</i>\n" +
            "\n" +
            "</div>";
    //    private static String profile ="<div style=\"font-size: 200%;margin-left: 5%;margin-top: 5%;position: absolute;display: table-cell;\"><form name=\"loginform\" id=\"loginform\" action=\"\n" +
//            "    %s\" method=\"post\"><div class=\"user-pass-wrap\"><label for=\"user_pass\">Имя</label><div class=\"wp-pwd\"><input class=\"form-control width-200 position-left\" maxlength=\"50\" type=\"text\" name=\"editmail\" value=\"\n" +
//            "    %s\"></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Фамилия</label><div class=\"wp-pwd\"><input class=\"form-control width-200 position-left\" maxlength=\"50\" type=\"text\" name=\"editmail\" value=\"\n" +
//            "    %s\"></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Отчество</label><div class=\"wp-pwd\"><input class=\"form-control width-200 position-left\" maxlength=\"50\" type=\"text\" name=\"editmail\" value=\"\n" +
//            "    %s\"></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Email</label><div class=\"wp-pwd\"><input class=\"form-control width-200 position-left\" maxlength=\"50\" type=\"text\" name=\"editmail\" value=\"\n" +
//            "    %s\"></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Пароль</label><div class=\"wp-pwd\"><input type=\"password\" name=\"pwd\" id=\"user_pass\" aria-describedby=\"login_error\" class=\"input password-input\" value=\"\" size=\"20\"></div></div><p class=\"submit\"><input type=\"submit\" name=\"wp-submit\" id=\"wp-submit\" class=\"button button-primary button-large\" value=\"\n" +
//            "    %s\"></p></form></div>";
    private static String profile1 = "<div style=\"font-size: 200%;margin-left: 5%;margin-top: 5%;position: absolute;display: table-cell;\"><form name=\"profile\" id=\"profile\" ><div class=\"user-pass-wrap\"><label for=\"user_pass\">Имя</label><div class=\"wp-pwd\"><input class=\"form-control width-200 position-left\" maxlength=\"50\" type=\"text\" name=\"editmail\" value=\"\n";
    private static String profile2 = "   \"></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Фамилия</label><div class=\"wp-pwd\"><input class=\"form-control width-200 position-left\" maxlength=\"50\" type=\"text\" name=\"editmail\" value=\"\n";
    private static String profile3 = "    \"></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Отчество</label><div class=\"wp-pwd\"><input class=\"form-control width-200 position-left\" maxlength=\"50\" type=\"text\" name=\"editmail\" value=\"\n";
    private static String profile4 = "    \"></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Email</label><div class=\"wp-pwd\"><input class=\"form-control width-200 position-left\" maxlength=\"50\" type=\"text\" name=\"editmail\" value=\"\n";
    private static String profile5 = "    \"></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Пароль</label><div class=\"wp-pwd\"><input type=\"password\" name=\"pwd\" id=\"user_pass\" aria-describedby=\"login_error\" class=\"input password-input\" value=\"\" size=\"20\"></div></div><p class=\"submit\"><input type=\"submit\" name=\"wp-submit\" id=\"wp-submit\" class=\"button button-primary button-large\" value=\"\n";
    private static String profile6 = "    \"></p></form></div>";
    private final static String addContent = "<div style=\"font-size: 200%;margin-left: 5%;margin-top: 5%;position: absolute;display: table-cell;\"><form name=\"loginform\" id=\"loginform\" action=\"%s\" method=\"post\" style=\"text-align: center;\"><div class=\"user-pass-wrap\"><label for=\"user_pass\">Название</label><div class=\"wp-pwd\"><p><input type=\"name\" name=\"name\" id=\"user_pass\" aria-describedby=\"login_error\" class=\"input password-input\" value=\"\" size=\"20\" style=\"\n" +
            "    width: 1000px;\n" +
            "\"></p></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Описание</label><div class=\"wp-pwd\"><p style=\"\n" +
            "\"><textarea name=\"title\" style=\"margin: 0px;width: -webkit-fill-available;height: 300px;\">Ваш текст</textarea></p></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Количество</label><div class=\"wp-pwd\"><p><input type=\"count\" name=\"count\" id=\"user_pass\" aria-describedby=\"login_error\" class=\"input password-input\" value=\"\" size=\"20\" style=\"\n" +
            "    width: -webkit-fill-available;\n" +
            "    \"></p></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Цена</label><div class=\"wp-pwd\"><p><input type=\"price\" name=\"price\" id=\"user_pass\" aria-describedby=\"login_error\" class=\"input password-input\" value=\"\" size=\"20\" style=\"\n" +
            "    width: -webkit-fill-available;\n" +
            "    \"></p></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Категория</label><div class=\"wp-pwd\"><p>\n" +
            "    <select multiple=\"\" name=\"category\" style=\"\n" +
            "    width: -webkit-fill-available;\n" +
            "\">\n" +
            "    <option disabled=\"\">Выберите категорию</option>\n" +
            "</select>\n" +
            "</p></div></div><div class=\"user-pass-wrap\"><label for=\"user_pass\">Ссылка на картинку</label><div class=\"wp-pwd\"><p><input type=\"picture\" name=\"picture\" id=\"user_pass\" aria-describedby=\"login_error\" class=\"input password-input\" value=\"\" size=\"20\" style=\"\n" +
            "    width: -webkit-fill-available;\n" +
            "    \"></p></div></div><p class=\"submit\"><input type=\"submit\" name=\"wp-submit\" id=\"wp-submit\" class=\"button button-primary button-large\" value=\"Применить\"></p></form></div>";

    public static String getLogin() {
        return login;
    }

    public static String getProfile(String name, String lastName, String surName, String email) {
        return profile1 + name + profile2 + surName + profile3 + lastName + profile4 + email + profile5 + "Применить" + profile6;
        //String.format(profile,"action1");//, name, surName);//, lastName, email, "Применить");
    }

    public static String getRegistration() {
        return profile1 + profile2 + profile3 + profile4 + profile5 + "Регистрация" + profile6;
    }

    public static String getAddContent() {
        return addContent;
    }

}
