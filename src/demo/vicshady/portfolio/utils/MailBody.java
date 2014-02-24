package demo.vicshady.portfolio.utils;

public class MailBody {

	public static String getBody(String name,String contact,String address)
	{
		String str=null;
		
		str="<td style=\"padding:40px 20px;\" id=\"yui_3_13_0_ym1_1_1392534689432_2560\">\n" +
	            "\t\t\t\t<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" id=\"yui_3_13_0_ym1_1_1392534689432_2559\">\n" +
	            "\t\t\t\t\t<tbody id=\"yui_3_13_0_ym1_1_1392534689432_2558\"><tr id=\"yui_3_13_0_ym1_1_1392534689432_2600\">\n" +
	            "\t\t\t\t\t\t<td align=\"left\" bgcolor=\"#272727\" style=\"padding:20px 10px;\" id=\"yui_3_13_0_ym1_1_1392534689432_2599\">\n" +
	            "\t\t\t\t\t\t\t<a rel=\"nofollow\" target=\"_blank\" href=\"https://www.mailkitchen.com\" id=\"yui_3_13_0_ym1_1_1392534689432_2604\">\n" +
	            "\t\t\t\t\t\t\t\t<img src=\"https://www.mailkitchen.com/images/logo-mk-en.png\" alt=\"MailKitchen\" title=\"MailKitchen\" border=\"0\" width=\"160\" height=\"30\" id=\"yui_3_13_0_ym1_1_1392534689432_2603\">\n" +
	            "\t\t\t\t\t\t\t</a>\n" +
	            "\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t</tr>\n" +
	            "\t\t\t\t\t<tr id=\"yui_3_13_0_ym1_1_1392534689432_2557\">\n" +
	            "\t\t\t\t\t\t<td align=\"left\" bgcolor=\"#FFFFFF\" style=\"color:#6F6E6E;font-size:16px;font-family:Lato, Helvetica, Arial, sans-serif;\" id=\"yui_3_13_0_ym1_1_1392534689432_2556\">\n" +
	            "\t\t\t\t\t\t\t<p align=\"center\" style=\"margin:30px 30px 0;\" id=\"yui_3_13_0_ym1_1_1392534689432_2555\">\n" +
	            "\t\t\t\t\t\t\t\t Dear <span style=\"color:#A7CA01;font-size:26px;\" id=\"yui_3_13_0_ym1_1_1392534689432_2597\"> "+ name +",</span>\n" +
	            "\t\t\t\t\t\t\t\t<br><br>\n" +
	            "\t\t\t\t\t\t\t\t<b>Welcome to <span style=\"color:#A7CA01;\">R</span>ajvi Designing</b><br>\n" +
	            "\t\t\t\t\t\t\t\t<b>and thank you for signing up on our Rajvi Designing Android Platform!</b>\n" +
	            "\t\t\t\t\t\t\t</p>\n" +
	            "\t\t\t\t\t\t\t<p style=\"margin:20px 30px 0;text-indent:20px;\">\n" +
	            "\t\t\t\t\t\t\t\tLots of new features will be added in the coming weeks for creating, sending and tracking your Portfolio - so stay tuned!<br>\n" +
	            "\t\t\t\t\t\t\t\tFrom time to time, we will send you a newsletter keeping you updated on our activities, new functionalities and features of our software suite.\n" +
	            "\t\t\t\t\t\t\t</p>\n" +
	            "\t\t\t\t\t\t\t<p style=\"margin:20px 30px 0;text-indent:20px;\">\n" +
	            "\t\t\t\t\t\t\t\t\n" +
	            "\t\t\t\t\t\t\t</p>\n" +
	            "\t\t\t\t\t\t\t<ul style=\"margin:20px 30px 0 60px;padding:0;color:#A7CA01;\">\n" +
	            "\t\t\t\t\t\t\t\t<li>\n" +
	            "\t\t\t\t\t\t\t\t\tName :<a rel=\"nofollow\" target=\"_blank\" href=\"https://mail.mailkitchen.com/modeles/aide/mk.download.php?langue=en&amp;guide=2\" style=\"color:#A7CA01;text-decoration:none;\">"+name+"</a>\n" +
	            "\t\t\t\t\t\t\t\t</li>\n" +
	            "\t\t\t\t\t\t\t\t<li>\n" +
	            "\t\t\t\t\t\t\t\t\tContact :<a rel=\"nofollow\" target=\"_blank\" href=\"https://mail.mailkitchen.com/modeles/aide/mk.download.php?langue=en&amp;guide=3\" style=\"color:#A7CA01;text-decoration:none;\">"+contact+"</a>\n" +
	            "\t\t\t\t\t\t\t\t</li>\n" +
	            "\t\t\t\t\t\t\t\t<li>\n" +
	            "\t\t\t\t\t\t\t\t\tAddress :<a rel=\"nofollow\" target=\"_blank\" href=\"https://mail.mailkitchen.com/modeles/aide/mk.download.php?langue=en&amp;guide=6\" style=\"color:#A7CA01;text-decoration:none;\">"+address+"</a>\n" +
	            "\t\t\t\t\t\t\t\t</li>\n" +
	            "\t\t\t\t\t\t\t</ul>\n" +
	            "\t\t\t\t\t\t\t<p align=\"center\" style=\"margin:20px 30px 30px;\">\n" +
	            "\t\t\t\t\t\t\t\t<b>We wish you beautiful email campaigns!</b>\n" +
	            "\t\t\t\t\t\t\t\t<br><br><br>\n" +
	            "\t\t\t\t\t\t\t\t<b><i><span style=\"color:#A7CA01;\">(</span> <span style=\"color:#A7CA01;\">R</span>ajvi Designing Team <span style=\"color:#A7CA01;\">)</span></i></b>\n" +
	            "\t\t\t\t\t\t\t</p>\n" +
	            "\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t</tr>\n" +
	            "\t\t\t\t\t<tr>\n" +
	            "\t\t\t\t\t\t<td align=\"center\" bgcolor=\"#EDEDED\" style=\"color:#6F6E6E;font-size:9px;font-family:Lato, Helvetica, Arial, sans-serif;padding:10px;\">\n" +
	            "\t\t\t\t\t\t\tThis email has been sent by <a rel=\"nofollow\" target=\"_blank\" href=\"https://www.mailkitchen.com/\" style=\"text-decoration:underline;color:#A7CA01;\">Rajvi Design</a>, an Online Email Marketing Platform for Rajvi Design by Vikalp.<br>\n" +
	            "\t\t\t\t\t\t\tPlease contact our customer service if you think that you’ve received this email by mistake.\n" +
	            "\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t</tr>\n" +
	            "\t\t\t\t\t<tr>\n" +
	            "\t\t\t\t\t\t<td bgcolor=\"#272727\" style=\"padding:10px;\">\n" +
	            "\t\t\t\t\t\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
	            "\t\t\t\t\t\t\t\t<tbody><tr>\n" +
	            "\t\t\t\t\t\t\t\t\t<td width=\"30%\" align=\"right\" style=\"padding-right:10px;border-right:2px solid #4E4E4E;\">\n" +
	            "\t\t\t\t\t\t\t\t\t\t<a rel=\"nofollow\" target=\"_blank\" href=\"https://www.mailkitchen.com\">\n" +
	            "\t\t\t\t\t\t\t\t\t\t\t<img src=\"https://www.mailkitchen.com/images/logo-mk-gris-en.png\" alt=\"MailKitchen\" title=\"MailKitchen\" border=\"0\" width=\"110\" height=\"30\">\n" +
	            "\t\t\t\t\t\t\t\t\t\t</a>\n" +
	            "\t\t\t\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t\t\t\t\t<td align=\"left\" style=\"padding-left:10px;color:#6F6E6E;font-size:12px;font-family:Lato, Helvetica, Arial, sans-serif;\">\n" +
	            "\t\t\t\t\t\t\t\t\t\tRajvi<span style=\"color:#A7CA01;\">D </span>esign<span style=\"color:#FFFFF;\"> (079) 66154709</span> <br>\n" +
	            "\t\t\t\t\t\t\t\t\t\t3,Near Giriraj 2 Near Vageshwari Bus Stop, Gopal Nagar Railway Station Road, Chandlodiya, Ahmedabad - 382481" +
	            "\t\t\t\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t\t\t\t</tr>\n" +
	            "\t\t\t\t\t\t\t</tbody></table>\n" +
	            "\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t</tr>\n" +
	            "\t\t\t\t</tbody></table>\n" +
	            "\t\t\t</td>";		
		
		return str;
	}
	
	public static String getBody(String name,String contact,String address,String orderdate)
	{
		String str=null;
		
		str="<td style=\"padding:40px 20px;\" id=\"yui_3_13_0_ym1_1_1392534689432_2560\">\n" +
	            "\t\t\t\t<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" id=\"yui_3_13_0_ym1_1_1392534689432_2559\">\n" +
	            "\t\t\t\t\t<tbody id=\"yui_3_13_0_ym1_1_1392534689432_2558\"><tr id=\"yui_3_13_0_ym1_1_1392534689432_2600\">\n" +
	            "\t\t\t\t\t\t<td align=\"left\" bgcolor=\"#272727\" style=\"padding:20px 10px;\" id=\"yui_3_13_0_ym1_1_1392534689432_2599\">\n" +
	            "\t\t\t\t\t\t\t<a rel=\"nofollow\" target=\"_blank\" href=\"https://www.mailkitchen.com\" id=\"yui_3_13_0_ym1_1_1392534689432_2604\">\n" +
	            "\t\t\t\t\t\t\t\t<img src=\"https://www.mailkitchen.com/images/logo-mk-en.png\" alt=\"MailKitchen\" title=\"MailKitchen\" border=\"0\" width=\"160\" height=\"30\" id=\"yui_3_13_0_ym1_1_1392534689432_2603\">\n" +
	            "\t\t\t\t\t\t\t</a>\n" +
	            "\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t</tr>\n" +
	            "\t\t\t\t\t<tr id=\"yui_3_13_0_ym1_1_1392534689432_2557\">\n" +
	            "\t\t\t\t\t\t<td align=\"left\" bgcolor=\"#FFFFFF\" style=\"color:#6F6E6E;font-size:16px;font-family:Lato, Helvetica, Arial, sans-serif;\" id=\"yui_3_13_0_ym1_1_1392534689432_2556\">\n" +
	            "\t\t\t\t\t\t\t<p align=\"center\" style=\"margin:30px 30px 0;\" id=\"yui_3_13_0_ym1_1_1392534689432_2555\">\n" +
	            "\t\t\t\t\t\t\t\t Dear <span style=\"color:#A7CA01;font-size:26px;\" id=\"yui_3_13_0_ym1_1_1392534689432_2597\"> "+ name +",</span>\n" +
	            "\t\t\t\t\t\t\t\t<br><br>\n" +
	            "\t\t\t\t\t\t\t\t<b>Welcome to <span style=\"color:#A7CA01;\">R</span>ajvi Designing</b><br>\n" +
	            "\t\t\t\t\t\t\t\t<b>and thank you for signing up on our Rajvi Designing Android Platform!</b>\n" +
	            "\t\t\t\t\t\t\t</p>\n" +
	            "\t\t\t\t\t\t\t<p style=\"margin:20px 30px 0;text-indent:20px;\">\n" +
	            "\t\t\t\t\t\t\t\tLots of new features will be added in the coming weeks for creating, sending and tracking your Portfolio - so stay tuned!<br>\n" +
	            "\t\t\t\t\t\t\t\tFrom time to time, we will send you a newsletter keeping you updated on our activities, new functionalities and features of our software suite.\n" +
	            "\t\t\t\t\t\t\t</p>\n" +
	            "\t\t\t\t\t\t\t<p style=\"margin:20px 30px 0;text-indent:20px;\">\n" +
	            "\t\t\t\t\t\t\t\t\n" +
	            "\t\t\t\t\t\t\t</p>\n" +
	            "\t\t\t\t\t\t\t<ul style=\"margin:20px 30px 0 60px;padding:0;color:#A7CA01;\">\n" +
	            "\t\t\t\t\t\t\t\t<li>\n" +
	            "\t\t\t\t\t\t\t\t\tName :<a rel=\"nofollow\" target=\"_blank\" href=\"https://mail.mailkitchen.com/modeles/aide/mk.download.php?langue=en&amp;guide=2\" style=\"color:#A7CA01;text-decoration:none;\">"+name+"</a>\n" +
	            "\t\t\t\t\t\t\t\t</li>\n" +
	            "\t\t\t\t\t\t\t\t<li>\n" +
	            "\t\t\t\t\t\t\t\t\tContact :<a rel=\"nofollow\" target=\"_blank\" href=\"https://mail.mailkitchen.com/modeles/aide/mk.download.php?langue=en&amp;guide=3\" style=\"color:#A7CA01;text-decoration:none;\">"+contact+"</a>\n" +
	            "\t\t\t\t\t\t\t\t</li>\n" +
	            "\t\t\t\t\t\t\t\t<li>\n" +
	            "\t\t\t\t\t\t\t\t\tAddress :<a rel=\"nofollow\" target=\"_blank\" href=\"https://mail.mailkitchen.com/modeles/aide/mk.download.php?langue=en&amp;guide=6\" style=\"color:#A7CA01;text-decoration:none;\">"+address+"</a>\n" +
	            "\t\t\t\t\t\t\t\t</li>\n" +
	            "\t\t\t\t\t\t\t\t<li>\n" +
	            "\t\t\t\t\t\t\t\t\tOrder Date :<a rel=\"nofollow\" target=\"_blank\" href=\"https://mail.mailkitchen.com/modeles/aide/mk.download.php?langue=en&amp;guide=6\" style=\"color:#A7CA01;text-decoration:none;\">"+orderdate+"</a>\n" +
	            "\t\t\t\t\t\t\t\t</li>\n" +
	            "\t\t\t\t\t\t\t</ul>\n" +
	            "\t\t\t\t\t\t\t<p align=\"center\" style=\"margin:20px 30px 30px;\">\n" +
	            "\t\t\t\t\t\t\t\t<b>We wish you beautiful email campaigns!</b>\n" +
	            "\t\t\t\t\t\t\t\t<br><br><br>\n" +
	            "\t\t\t\t\t\t\t\t<b><i><span style=\"color:#A7CA01;\">(</span> <span style=\"color:#A7CA01;\">R</span>ajvi Designing Team <span style=\"color:#A7CA01;\">)</span></i></b>\n" +
	            "\t\t\t\t\t\t\t</p>\n" +
	            "\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t</tr>\n" +
	            "\t\t\t\t\t<tr>\n" +
	            "\t\t\t\t\t\t<td align=\"center\" bgcolor=\"#EDEDED\" style=\"color:#6F6E6E;font-size:9px;font-family:Lato, Helvetica, Arial, sans-serif;padding:10px;\">\n" +
	            "\t\t\t\t\t\t\tThis email has been sent by <a rel=\"nofollow\" target=\"_blank\" href=\"https://www.mailkitchen.com/\" style=\"text-decoration:underline;color:#A7CA01;\">Rajvi Design</a>, an Online Email Marketing Platform for Rajvi Design by Vikalp.<br>\n" +
	            "\t\t\t\t\t\t\tPlease contact our customer service if you think that you’ve received this email by mistake.\n" +
	            "\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t</tr>\n" +
	            "\t\t\t\t\t<tr>\n" +
	            "\t\t\t\t\t\t<td bgcolor=\"#272727\" style=\"padding:10px;\">\n" +
	            "\t\t\t\t\t\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
	            "\t\t\t\t\t\t\t\t<tbody><tr>\n" +
	            "\t\t\t\t\t\t\t\t\t<td width=\"30%\" align=\"right\" style=\"padding-right:10px;border-right:2px solid #4E4E4E;\">\n" +
	            "\t\t\t\t\t\t\t\t\t\t<a rel=\"nofollow\" target=\"_blank\" href=\"https://www.mailkitchen.com\">\n" +
	            "\t\t\t\t\t\t\t\t\t\t\t<img src=\"https://www.mailkitchen.com/images/logo-mk-gris-en.png\" alt=\"MailKitchen\" title=\"MailKitchen\" border=\"0\" width=\"110\" height=\"30\">\n" +
	            "\t\t\t\t\t\t\t\t\t\t</a>\n" +
	            "\t\t\t\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t\t\t\t\t<td align=\"left\" style=\"padding-left:10px;color:#6F6E6E;font-size:12px;font-family:Lato, Helvetica, Arial, sans-serif;\">\n" +
	            "\t\t\t\t\t\t\t\t\t\tRajvi<span style=\"color:#A7CA01;\">D </span>esign<span style=\"color:#FFFFF;\"> (079) 66154709</span> <br>\n" +
	            "\t\t\t\t\t\t\t\t\t\t3,Near Giriraj 2 Near Vageshwari Bus Stop, Gopal Nagar Railway Station Road, Chandlodiya, Ahmedabad - 382481" +
	            "\t\t\t\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t\t\t\t</tr>\n" +
	            "\t\t\t\t\t\t\t</tbody></table>\n" +
	            "\t\t\t\t\t\t</td>\n" +
	            "\t\t\t\t\t</tr>\n" +
	            "\t\t\t\t</tbody></table>\n" +
	            "\t\t\t</td>";		
		
		return str;
	}
}
