package org.lotuscloud.master.webhandler;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class HTML {

    public static final String footer = "</body>" +
            "</html>";
    public static final String style = "@import url('https://fonts.googleapis.com/css?family=Roboto');\n" +
            "\n" +
            "html, body {\n" +
            "    font-family: 'Roboto', sans-serif;\n" +
            "}\n" +
            "\n" +
            "::-webkit-input-placeholder { /* Chrome/Opera/Safari */\n" +
            "    font-family: 'Roboto', sans-serif;\n" +
            "}\n" +
            "\n" +
            "::-moz-placeholder { /* Firefox 19+ */\n" +
            "    font-family: 'Roboto', sans-serif;\n" +
            "}\n" +
            "\n" +
            ":-ms-input-placeholder { /* IE 10+ */\n" +
            "    font-family: 'Roboto', sans-serif;\n" +
            "}\n" +
            "\n" +
            ":-moz-placeholder { /* Firefox 18- */\n" +
            "    font-family: 'Roboto', sans-serif;\n" +
            "}\n" +
            "\n" +
            "ul {\n" +
            "    list-style-type: none;\n" +
            "    margin: 0;\n" +
            "    padding: 0;\n" +
            "    overflow: hidden;\n" +
            "}\n" +
            "\n" +
            "li {\n" +
            "    float: left;\n" +
            "    background-color: #e1e1e1;\n" +
            "}\n" +
            "\n" +
            "li a {\n" +
            "    display: block;\n" +
            "    color: #4c4c4c;\n" +
            "    text-align: center;\n" +
            "    padding: 10px 8px;\n" +
            "    text-decoration: none;\n" +
            "}\n" +
            "\n" +
            "li a:hover {\n" +
            "    color: #fff;\n" +
            "    background-color: #114579;\n" +
            "}\n" +
            "\n" +
            ".active {\n" +
            "    background-color: #0F3B68;\n" +
            "    color: #fff;\n" +
            "}\n" +
            "\n" +
            ".right {\n" +
            "    float: right;\n" +
            "}\n" +
            "\n" +
            ".left {\n" +
            "    float: left;\n" +
            "}\n" +
            "\n" +
            ".box {\n" +
            "    width: 60%;\n" +
            "    border-top: 8px solid #114579;\n" +
            "    border-bottom: 8px solid #114579;\n" +
            "    padding-top: 20px;\n" +
            "    padding-bottom: 20px;\n" +
            "    text-align: center;\n" +
            "    margin: 3% auto;\n" +
            "    background-color: #f9f9f9;\n" +
            "    box-shadow: #c2c2c2 7px 7px 1px;\n" +
            "}\n" +
            "\n" +
            ".gray {\n" +
            "    color: #696969;\n" +
            "}\n" +
            "\n" +
            "hr {\n" +
            "    width: 70%;\n" +
            "}\n" +
            "\n" +
            "textarea {\n" +
            "    padding: 5px;\n" +
            "    margin-bottom: 10px;\n" +
            "    margin-top: 10px;\n" +
            "    width: 50%;\n" +
            "    height: 100px;\n" +
            "    resize: none;\n" +
            "    outline: none;\n" +
            "    border: none;\n" +
            "    border-bottom: #c1c1c1 solid 2px;\n" +
            "    font-family: 'Roboto', sans-serif;\n" +
            "}\n" +
            "\n" +
            "textarea:focus {\n" +
            "    border-bottom: #5193e1 solid 2px;\n" +
            "}\n" +
            "\n" +
            ".login {\n" +
            "    min-width: 300px;\n" +
            "    width: 40%;\n" +
            "    border-top: 8px solid #114579;\n" +
            "    border-bottom: 8px solid #114579;\n" +
            "    padding-top: 20px;\n" +
            "    padding-bottom: 20px;\n" +
            "    text-align: center;\n" +
            "    margin: 3% auto;\n" +
            "    background-color: #f9f9f9;\n" +
            "    box-shadow: #c2c2c2 7px 7px 1px;\n" +
            "}\n" +
            "\n" +
            "input {\n" +
            "    border: none;\n" +
            "    font-size: 20px;\n" +
            "    margin-top: 20px;\n" +
            "    padding-right: 5px;\n" +
            "    padding-left: 5px;\n" +
            "    border-bottom: #c1c1c1 solid 2px;\n" +
            "    outline: none;\n" +
            "}\n" +
            "\n" +
            "input:focus {\n" +
            "    border-bottom: #5193e1 solid 2px;\n" +
            "}\n" +
            "\n" +
            ".ticket_item {\n" +
            "    color: #333333;\n" +
            "    text-decoration: none;\n" +
            "    text-align: center;\n" +
            "    font-size: 18px;\n" +
            "}\n" +
            "\n" +
            ".ticket_item:hover {\n" +
            "    color: #131313;\n" +
            "}\n" +
            "\n" +
            "button {\n" +
            "    background-color: #e1e1e1;\n" +
            "    color: #4c4c4c;\n" +
            "    text-decoration: none;\n" +
            "    border: none;\n" +
            "    font-weight: bold;\n" +
            "    font-size: 15px;\n" +
            "    padding: 10px;\n" +
            "    box-shadow: #c2c2c2 3px 3px 1px;\n" +
            "}\n" +
            "\n" +
            "button:hover {\n" +
            "    color: #fff;\n" +
            "    background-color: #114579;\n" +
            "}\n" +
            "\n" +
            ".notice {\n" +
            "    color: #4c4c4c;\n" +
            "    text-decoration: none;\n" +
            "    text-align: center;\n" +
            "}\n" +
            "\n" +
            ".notice:hover {\n" +
            "    color: #191919;\n" +
            "}\n" +
            "\n" +
            "div.notice {\n" +
            "    position: relative;\n" +
            "    left: 50%;\n" +
            "    bottom: 1px;\n" +
            "    transform: translate(-50%, -50%);\n" +
            "    margin: 0 auto;\n" +
            "}";
    public static final String head = "<!DOCTYPE html>" +
            "<head>" +
            "<title>LotusCloud.org CP</title>" +
            "<style>" + HTML.style +
            "</style>" +
            "</head>" +
            "<body>";
}