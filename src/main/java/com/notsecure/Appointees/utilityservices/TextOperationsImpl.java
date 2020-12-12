package com.notsecure.Appointees.utilityservices;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextOperationsImpl implements TextOperations{
@Override
public String doExample1(String text) {
   
   text = "08:00,11:30,13:00,15:30";
   
   int bufferTime = 5;
   int serviceTime = 25;
   List<LocalTime> localTimes = Arrays.asList(text.split(",")).stream().map(str -> LocalTime.parse(str)).collect(Collectors.toList());
   
   StringBuilder sb = new StringBuilder("");
   LocalTime availableTime = localTimes.get(0);
   int flag = 0;
   
   while(availableTime.isBefore(localTimes.get(localTimes.size()-1)) && flag<localTimes.size()) {
      if(availableTime.plusMinutes(serviceTime+bufferTime).isBefore(localTimes.get(flag+1)) || availableTime.plusMinutes(serviceTime+bufferTime).equals(localTimes.get(flag+1))) {
         sb.append(availableTime.toString() + ",");
         availableTime = availableTime.plusMinutes(serviceTime+bufferTime);
      }else {
         if(flag+2>localTimes.size()) break;
         flag+=2;
         availableTime = localTimes.get(flag);
      }
   }
   
   return sb.toString();
}

@Override
public String doExample1(String str, String str2) {
   return null;
}
}
