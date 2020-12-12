package com.notsecure.Appointees.utilityservices;

/*
* Let's do some assumptions for Saturday, 12/12/2020
* Company default time for is "08:00,18:00"
* Then there is another row saying, branch-TX has a default time set as following:
* "08:00,11:30,13:00,15:30"
* In this condition we totally ignore the company data.
* Then, here it comes the service provider, service provider has a separate row in the table
* along with the service information, it says that Service-Provider-1 has scheduled for Service-A
* from 8:00 to 12:00 pm. And Service-B from 12:30 to 16:00.
* In this case service provider's data overrides the branches' open hours too.
* Let's make it a bit more complicated..
* Then in the customday data, we see that, Service-Provider-1's 12/12/2020 service-A schedule is
* overridden to 10:00,12:00, meaning that he'll come to work 2 hours late based on his def. schedule.
* In this case Service, Service-Provider's final work hours for 12/12/2020 becomes
* 10:00,12:00,12:30,16:00
* Getting to this information is not enough as you may guess.
* Next thing to do is to get the time slots.
*
* */

/*
Let's say the provider is providing two different services:
Morning each service takes 30 min in total.
Afternoon, service-B takes only 15 minutes in total.

Service-Provider-1
   if totalServiceTime-A: 30min
   Service-A: "10:00,10:30,11:00,11:30"
   
   if totalServiceTime-B: 15min
   Service-B: "13:00,13:15,13:30,13:45,14:00,14:15..,15:45"
*/

/*
From All these, to get he default hours for a provider, we should
   1- if available, retrieve the service provider's data from Weekly Default Work Hours table.
    It will be a list, we can see the time windows for each service he's assigned to.
   2- if service-provider's data is not available in the table, then retrieve branch's data.
   3- if branch's data is not available, retrieve company's data.
 */

public interface TextOperations {

public String doExample1(String str);
public String doExample1(String str, String str2);



}
