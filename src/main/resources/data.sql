INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(100,'234 XYZ',NULL,'HOUSTON','USA','TX','78444');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(101,'2343 limen rd',NULL,'AUSTIN','USA','TX','75566');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(102,'457 flyinn Dr',NULL,'HOUSTON','USA','TX','72688');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(103,'2344 limenoto rd',NULL,'AUSTIN','USA','TX','69810');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(104,'235 XYZ',NULL,'HOUSTON','USA','TX','66932');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(105,'2344 limen rd',NULL,'AUSTIN','USA','TX','64054');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(106,'458 flyinn Dr',NULL,'HOUSTON','USA','TX','61176');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(107,'2345 limenoto rd',NULL,'AUSTIN','USA','TX','58298');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(108,'2346 limenoto rd',NULL,'AUSTIN','USA','TX','58299');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(109,'459 flyinn Dr',NULL,'HOUSTON','USA','TX','58300');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(110,'2347 limenoto rd',NULL,'AUSTIN','USA','TX','58301');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(111,'2347 limenoto rd',NULL,'AUSTIN','USA','TX','58301');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(112,'2347 limenoto rd',NULL,'AUSTIN','USA','TX','58301');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(113,'2347 limenoto rd',NULL,'AUSTIN','USA','TX','58301');
INSERT INTO ADDRESS(ID,ADDRESS_LINE1,ADDRESS_LINE2,CITY,COUNTRY,STATE,ZIP_CODE) VALUES(114,'2347 limenoto rd',NULL,'AUSTIN','USA','TX','58301');

INSERT INTO ACCOUNT_INFO(ID,NUMBER_OF_BOOKINGS_LEFT,PLAN_TYPE,VALID_UNTIL) VALUES(2000,99,'PREMIUM','2021-03-01');
-- INSERT INTO PUBLIC_INFO(ID,BOOKING_PAGE_TITLE,BUSINESS_CATEGORY,BUSINESS_HIGHLIGHTS,BUSINESS_NAME,CUSTOMER_SUPPORT_EMAIL,CUSTOMER_SUPPORT_PHONE,IMAGE_URL,WEBSITE_LINK) VALUES(550,'Welcome to Urban Day Spa !','Beauty','Urban Day Spa','Urban Day Spa','support@urbandayspa.com','833-541-6480','https://clients.mindbodyonline.com/studios/UrbanDaySpaSpringTX/logo.gif','https://clients.mindbodyonline.com/');

INSERT INTO COMPANY(ID,ACTIVE_ACCOUNT,BOOKING_PAGE_TITLE,BUSINESS_CATEGORY,BUSINESS_HIGHLIGHTS,BUSINESS_NAME,CUSTOMER_SUPPORT_EMAIL,CUSTOMER_SUPPORT_PHONE,IMAGE_URL,WEBSITE_LINK,ACCOUNT_INFO_ID,ADDRESS_ID) VALUES(500,true,'Welcome to Urban Day Spa!','Beauty/Health','Urban Day Spa, with 2 locations in the Greater Houston Area (Klein, Spring TX area and Copperfield, Houston area), is your best choice for enjoying an outstanding relaxation experience involving massage therapy, body therapies & skin care treatments in Houston, Texas. At Urban Day Spa you will enjoy heavenly stress relief, improving coping skills and overall wellness in day-to-day life. Our luxurious skin care therapies help keep you feeling and looking your best!','Urban Spa Day','support@urbandayspa.com','832-698-1544','https://clients.mindbodyonline.com/studios/UrbanDaySpaSpringTX/logo.gif','https://urbandayspa.com/',2000,100);

INSERT INTO BRANCH(ID,ACTIVE_ACCOUNT,BOOKING_PAGE_TITLE,BUSINESS_CATEGORY,BUSINESS_HIGHLIGHTS,BUSINESS_NAME,CUSTOMER_SUPPORT_EMAIL,CUSTOMER_SUPPORT_PHONE,IMAGE_URL,WEBSITE_LINK,ADDRESS_ID,SEPARATE_BILLING,ZONE_ID,ACCOUNT_INFO_ID,COMPANY_ID) VALUES(300,true,'Welcome to Urban Day Spa at Copperfield!','Health/Beauty','All sort of care provided at CopperField','Urban Day Spa in Copperfiled','support@urbandayspa.com','832-698-1544','https://clients.mindbodyonline.com/studios/UrbanDaySpaSpringTX/logo.gif','https://urbandayspa.com/',102,false,'America/Chicago',NULL,500);
INSERT INTO BRANCH(ID,ACTIVE_ACCOUNT,BOOKING_PAGE_TITLE,BUSINESS_CATEGORY,BUSINESS_HIGHLIGHTS,BUSINESS_NAME,CUSTOMER_SUPPORT_EMAIL,CUSTOMER_SUPPORT_PHONE,IMAGE_URL,WEBSITE_LINK,ADDRESS_ID,SEPARATE_BILLING,ZONE_ID,ACCOUNT_INFO_ID,COMPANY_ID) VALUES(301,true,'Welcome to Urban Day Spa at Louetta!','Health/Beauty','All sort of care provided at Louetta','Urban Day Spa in Copperfiled','support@urbandayspa.com','832-698-1545','https://clients.mindbodyonline.com/studios/UrbanDaySpaSpringTX/logo.gif','https://urbandayspa.com/',103,false,'America/Chicago',NULL,500);

INSERT INTO CLIENT(ID,EMAIL,NAME,PHONE,ADDRESS_ID) VALUES(400,'CLIENT@GMAIL.COM','ALI','5049877856',101);
INSERT INTO CLIENT(ID,EMAIL,NAME,PHONE,ADDRESS_ID) VALUES(401,'CLIENT1@GMAIL.COM','VELI','5049877857',104);
INSERT INTO CLIENT(ID,EMAIL,NAME,PHONE,ADDRESS_ID) VALUES(402,'CLIENT2@GMAIL.COM','KIRKOF','5049877858',105);
INSERT INTO CLIENT(ID,EMAIL,NAME,PHONE,ADDRESS_ID) VALUES(403,'CLIENT3@GMAIL.COM','DELI','5049877859',106);
INSERT INTO CLIENT(ID,EMAIL,NAME,PHONE,ADDRESS_ID) VALUES(404,'CLIEN4@GMAIL.COM','SELIM','5049877860',107);

INSERT INTO CUSTOM_DAYS(ID,CUSTOM_DATE,DAILY_WORK_HOURS,DATE_CREATED,REASON,BRANCH_ID,COMPANY_ID,SERVICE_PROVIDER_ID) VALUES(600,'2020-12-30','08:00,13:00','2020-12-10T11:18','EARLY LEAVE BEFORE NEW YEARY',300,500,NULL);
INSERT INTO CUSTOM_DAYS(ID,CUSTOM_DATE,DAILY_WORK_HOURS,DATE_CREATED,REASON,BRANCH_ID,COMPANY_ID,SERVICE_PROVIDER_ID) VALUES(601,'2020-12-25','12:00,20:00','2020-12-10T11:18','CHRISTMAS SCHEDULE',301,500,NULL);

INSERT INTO MONTHLY_BUSINESS_WORK_DAYS(ID,FIRST_DAY_OF_MONTH,MONTHLY_DATA,BRANCH_ID,COMPANY_ID) VALUES(700,'2020-12-01','1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1',300,500);
INSERT INTO MONTHLY_BUSINESS_WORK_DAYS(ID,FIRST_DAY_OF_MONTH,MONTHLY_DATA,BRANCH_ID,COMPANY_ID) VALUES(701,'2020-12-01','1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1',301,500);

INSERT INTO PERMISSIONS(ID,ADD_NEW_USER,MODIFY_UPCOMING_APPOINTMENTS) VALUES(900,TRUE,TRUE);
INSERT INTO PERMISSIONS(ID,ADD_NEW_USER,MODIFY_UPCOMING_APPOINTMENTS) VALUES(901,FALSE,FALSE);

INSERT INTO SERVICE(ID,ALLOWED_CANCELLATION_TIME_WINDOW_PRIOR_TO_APPOINTMENT,ALLOWED_DAYS_IN_ADVANCE_APPOINTMENT_BOOKINGS,BUFFER_TIME,DESCRIPTION,IMAGE_LINK,DURATION,DURATION_VISIBLE_ONBP,PRICE_AVAILABLE_ONBP,SERVICE_AVAILABLE_ONBP,NAME,PRICE,SERVICE_INFO_PUBLIC_TO_ALL_BRANCHES,SERVICE_PAGE_TITLE,BRANCH_ID,COMPANY_ID) VALUES(1000,50,90,30,'Each Massage Is Customized For Your Needs. Get The Experience You Deserve. Call Today! Professional. Convenient. Upscale. Resort-Quality. Open 7 Days A Week. Services: Swedish Massage, Deep Tissue Massage, Sports Massage, Couples Massage, Prenatal Massage.',null ,30,FALSE,TRUE,TRUE,'Massage Therapy',50.00,TRUE,'Massage Therapy',300,500);
INSERT INTO SERVICE(ID,ALLOWED_CANCELLATION_TIME_WINDOW_PRIOR_TO_APPOINTMENT,ALLOWED_DAYS_IN_ADVANCE_APPOINTMENT_BOOKINGS,BUFFER_TIME,DESCRIPTION,IMAGE_LINK,DURATION,DURATION_VISIBLE_ONBP,PRICE_AVAILABLE_ONBP,SERVICE_AVAILABLE_ONBP,NAME,PRICE,SERVICE_INFO_PUBLIC_TO_ALL_BRANCHES,SERVICE_PAGE_TITLE,BRANCH_ID,COMPANY_ID) VALUES(1001,60,90,10,'Facial massage helps promote healthy skin while relaxing your facial muscles. It has a relaxing and rejuvenating effect, helping you look and feel better.',NULL,20,FALSE,TRUE,TRUE,'Facials',99.99,TRUE,'Facials',300,500);
INSERT INTO SERVICE(ID,ALLOWED_CANCELLATION_TIME_WINDOW_PRIOR_TO_APPOINTMENT,ALLOWED_DAYS_IN_ADVANCE_APPOINTMENT_BOOKINGS,BUFFER_TIME,DESCRIPTION,IMAGE_LINK,DURATION,DURATION_VISIBLE_ONBP,PRICE_AVAILABLE_ONBP,SERVICE_AVAILABLE_ONBP,NAME,PRICE,SERVICE_INFO_PUBLIC_TO_ALL_BRANCHES,SERVICE_PAGE_TITLE,BRANCH_ID,COMPANY_ID) VALUES(1002,60,90,30,'help manage a health condition or enhance wellness. It involves manipulating the soft tissues of the body',NULL,10,FALSE,TRUE,TRUE,'Body Treatments',149.99,TRUE,'Body Treatments',300,500);
INSERT INTO SERVICE(ID,ALLOWED_CANCELLATION_TIME_WINDOW_PRIOR_TO_APPOINTMENT,ALLOWED_DAYS_IN_ADVANCE_APPOINTMENT_BOOKINGS,BUFFER_TIME,DESCRIPTION,IMAGE_LINK,DURATION,DURATION_VISIBLE_ONBP,PRICE_AVAILABLE_ONBP,SERVICE_AVAILABLE_ONBP,NAME,PRICE,SERVICE_INFO_PUBLIC_TO_ALL_BRANCHES,SERVICE_PAGE_TITLE,BRANCH_ID,COMPANY_ID) VALUES(1003,60,90,15,'Each Massage Is Customized For Your Needs. Get The Experience You Deserve. Call Today! Professional. Convenient. Upscale. Resort-Quality. Open 7 Days A Week. Services: Swedish Massage, Deep Tissue Massage, Sports Massage, Couples Massage, Prenatal Massage.',NULL,15,FALSE,TRUE,TRUE,'Massage Therapy-Shorter-Version',149.99,TRUE,'Massage Therapy',300,500);

INSERT INTO USER(ID,DATE_CREATED,DISPLAY_NAME,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,PHONE_NUMBER,ADDRESS_ID) VALUES(1200,'2020-12-10T11:18','Anna','anna@urbanspaday.com','anna','Lopez','dontCopy','832-958-99-50',108);
INSERT INTO USER(ID,DATE_CREATED,DISPLAY_NAME,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,PHONE_NUMBER,ADDRESS_ID) VALUES(1201,'2020-12-10T11:19','Salih','salih@urbanspaday.com','salih','Dorma','kopylama','832-958-99-51',109);
INSERT INTO USER(ID,DATE_CREATED,DISPLAY_NAME,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,PHONE_NUMBER,ADDRESS_ID) VALUES(1202,'2020-12-10T11:20','Provider-1','Selma@urbanspaday.com','selma','Korp','23232323','832-958-99-52',110);
INSERT INTO USER(ID,DATE_CREATED,DISPLAY_NAME,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,PHONE_NUMBER,ADDRESS_ID) VALUES(1203,'2020-12-10T11:21','Provider-2','Selma@urbanspaday.com','selma','Korp','23232324','832-958-99-53',111);
INSERT INTO USER(ID,DATE_CREATED,DISPLAY_NAME,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,PHONE_NUMBER,ADDRESS_ID) VALUES(1204,'2020-12-10T11:22','Provider-3','Selma@urbanspaday.com','selma','Korp','23232325','832-958-99-54',112);
INSERT INTO USER(ID,DATE_CREATED,DISPLAY_NAME,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,PHONE_NUMBER,ADDRESS_ID) VALUES(1205,'2020-12-10T11:23','Provider-4','Selma@urbanspaday.com','selma','Korp','23232326','832-958-99-55',113);

INSERT INTO SERVICE_PROVIDER(ID,SERVICE_PROVIDER_AVAILABLE_ONBP,BRANCH_ID,COMPANY_ID,USER_ID) VALUES(1100,TRUE,300,500,1202);
INSERT INTO SERVICE_PROVIDER(ID,SERVICE_PROVIDER_AVAILABLE_ONBP,BRANCH_ID,COMPANY_ID,USER_ID) VALUES(1101,TRUE,300,500,1203);
INSERT INTO SERVICE_PROVIDER(ID,SERVICE_PROVIDER_AVAILABLE_ONBP,BRANCH_ID,COMPANY_ID,USER_ID) VALUES(1102,TRUE,300,500,1204);
INSERT INTO SERVICE_PROVIDER(ID,SERVICE_PROVIDER_AVAILABLE_ONBP,BRANCH_ID,COMPANY_ID,USER_ID) VALUES(1103,TRUE,301,500,1205);

INSERT INTO SERVICE_PROVIDER_SERVICES(SERVICE_PROVIDER_ID,SERVICES_ID) VALUES(1100,1000);
INSERT INTO SERVICE_PROVIDER_SERVICES(SERVICE_PROVIDER_ID,SERVICES_ID) VALUES(1100,1001);
INSERT INTO SERVICE_PROVIDER_SERVICES(SERVICE_PROVIDER_ID,SERVICES_ID) VALUES(1102,1002);
INSERT INTO SERVICE_PROVIDER_SERVICES(SERVICE_PROVIDER_ID,SERVICES_ID) VALUES(1103,1003);

INSERT INTO USER_ROLE(ID,DATE_CREATED,DEFAULT_ROLE,PERSONNEL_HIGHLIGHTS,ROLE_VALID,BRANCH_ID,COMPANY_ID,PERMISSIONS_ID,ROLE_ASSIGNED_BY_ID,USER_ID) VALUES(1300,'2020-12-10T11:18',TRUE,NULL,TRUE,300,500,900,null,1200);
INSERT INTO USER_ROLE(ID,DATE_CREATED,DEFAULT_ROLE,PERSONNEL_HIGHLIGHTS,ROLE_VALID,BRANCH_ID,COMPANY_ID,PERMISSIONS_ID,ROLE_ASSIGNED_BY_ID,USER_ID) VALUES(1301,'2020-12-10T11:19',TRUE,'Great at his job! Staisfaction Guranteed',TRUE,300,500,901,1200,1202);

INSERT INTO WEEKLY_DEFAULT_WORK_HOURS(ID,EFFECTIVE_BY,FRIDAY,MONDAY,SATURDAY,SUNDAY,THURSDAY,TUESDAY,WEDNESDAY,BRANCH_ID,COMPANY_ID,SERVICE_ID,SERVICE_PROVIDER_ID) VALUES(1600,'2021-04-30','08:00,12:00,13:00,18:00','08:00,12:00,13:00,18:01','08:00,13:00',null,'08:00,12:00,13:00,18:04','08:00,12:00,13:00,18:05','08:00,12:00,13:00,18:06',null,500,null,null);
INSERT INTO WEEKLY_DEFAULT_WORK_HOURS(ID,EFFECTIVE_BY,FRIDAY,MONDAY,SATURDAY,SUNDAY,THURSDAY,TUESDAY,WEDNESDAY,BRANCH_ID,COMPANY_ID,SERVICE_ID,SERVICE_PROVIDER_ID) VALUES(1601,'2021-04-30','08:00,16:00','default','default','default','default','default','default',300,500,null,null);
INSERT INTO WEEKLY_DEFAULT_WORK_HOURS(ID,EFFECTIVE_BY,FRIDAY,MONDAY,SATURDAY,SUNDAY,THURSDAY,TUESDAY,WEDNESDAY,BRANCH_ID,COMPANY_ID,SERVICE_ID,SERVICE_PROVIDER_ID)
VALUES(1602,'2021-04-30','08:00,12:00','08:00,12:00',null,null,'08:00,12:00','default','default',300,500,1000,1100);
INSERT INTO WEEKLY_DEFAULT_WORK_HOURS(ID,EFFECTIVE_BY,FRIDAY,MONDAY,SATURDAY,SUNDAY,THURSDAY,TUESDAY,WEDNESDAY,BRANCH_ID,COMPANY_ID,SERVICE_ID,SERVICE_PROVIDER_ID)
VALUES(1603,'2021-04-30','12:30,16:00','12:30,16:00','default','default','12:00,14:00',null,null,300,500,1001,1100);


INSERT INTO APPOINTMENT(ID,APPOINTMENT_DATE_TIME,BOOKING_CONFIRMATION_NUMBER,CANCELLED,DATE_CREATED,CLIENT_ID,SERVICE_ID,SERVICE_PROVIDER_ID) VALUES(200,'2020-12-10T11:18','FDSF5SDXT',FALSE,'2020-11-18',400,1000,1100);

INSERT INTO NOTE(ID,DATE_CREATED,NOTE,APPOINTMENT_ID,USER_ID) VALUES(800,'2020-12-10T11:18','dONT EVER cANCEL THIS APPOINTMENT',200,1200);

------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------
