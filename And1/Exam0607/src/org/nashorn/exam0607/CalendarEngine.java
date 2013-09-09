package org.nashorn.exam0607;

import java.util.*;

public class CalendarEngine
{
	Calendar oCalendar = Calendar.getInstance( );  
	private int			gyear, gmonth, gday;
	
	public int getCurrentYear()
	{
		return this.gyear;
	}
	
	public int getCurrentMonth()
	{
		return this.gmonth;
	}
	
	public int getCurrentDay()
	{
		return this.gday;
	}
	
	public void setCurrentDay(int day)
	{
		this.gday = day;
	}
	
	public int isLeapYear(int year) /* year�� �����̸� 1��, �ƴϸ� 0�� �����ϴ� �Լ� */
	{
	    if (year % 400 == 0)
			return(1);
	    else if (year % 100 == 0) 
				return(0);
			else if(year % 4 == 0)   
					return(1);
				else
					return(0);
	}

	public int getLastDay(int year, int month) /* year�� month���� ������ ��¥�� ������ �����ϴ� �Լ� 31��,30��,29��,28�� �߿��� */
	{
	    if(month == 2)
	    {
	        if (isLeapYear(year) == 1) 
				return(29);
	        else
				return(28);
	    }
	    else if(month == 2 || month == 4 || month == 6 || month == 9 || month == 11)  
				return(30);
			else  
				return(31);
	}

	public int zeller(int year, int month, int day) /* year�� month�� day���� ������ �����ϴ� �Լ� �Ͽ����� 0, �������� 1, ȭ������ 2,... */
	{
	    int year_of_centry, centry, day_of_week;

	    if(month == 1 || month == 2)  
			year--;
	    month			= (month + 9) % 12 + 1;
	    year_of_centry	= year % 100;
	    centry			= year / 100;
	    day_of_week		= ((13*month-1)/5 + day + year_of_centry + year_of_centry/4 + centry/4 - 2*centry) % 7;
	    if (day_of_week < 0) 
			day_of_week = (day_of_week + 7) % 7;
	    
		return (day_of_week);
	}

	public void movePrevMonth()
	{
		if (gyear > 1 || gmonth > 1)
		{
			if (gmonth > 1)
			{
				gmonth--;
				if (gday > getLastDay(gyear, gmonth))
					gday = getLastDay(gyear, gmonth);
			}
			else
			{
				gmonth = 12;
				gyear--;
				if (gday > getLastDay(gyear, gmonth))
					gday = getLastDay(gyear, gmonth);
			}
		}
	}

	public void moveNextMonth()
	{
		if (gyear < 9999 || gmonth < 12)
		{
			if (gmonth < 12)
			{
				gmonth++;
				if (gday > getLastDay(gyear, gmonth))
					gday = getLastDay(gyear, gmonth);
			}
			else
			{
				gmonth = 1;
				gyear++;
				if (gday > getLastDay(gyear, gmonth))
					gday = getLastDay(gyear, gmonth);
			}
		}
	}

	public void movePrevYear()
	{
		if (gyear > 1)  
			gyear--;
	}
	 
	public void moveNextYear()
	{
		if (gyear < 9999) 
			gyear++;
	}                        

	public void moveCurrentDate()
	{
		gyear	= oCalendar.get(Calendar.YEAR);
		gmonth	= oCalendar.get(Calendar.MONTH)+1;
		gday	= oCalendar.get(Calendar.DAY_OF_MONTH);
	}
}

