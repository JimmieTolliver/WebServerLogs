import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
	
	private ArrayList<LogEntry> records;
	
	public LogAnalyzer() {
		records = new ArrayList<LogEntry>();
	}
	
	public void readFile(String filename) {
		FileResource fr = new FileResource(filename);
		for (String s: fr.lines()) {
			records.add(WebLogParser.parseEntry(s));
		}
	}
	
	public void printAll() {
		for(LogEntry le: records) {
			System.out.println(le);
		}
	}
	
	public int countUniqueIPs() {
		//uniqueIps starts as an empty list
		ArrayList<String> uniqueIPs = new ArrayList<String>();
		//for each element le in records
		for(LogEntry le: records) {		
			//ipAddr is le's ipAddress
			String ipAddr = le.getIpAddress();
				//if ipAddr is not in uniqueIPs{
				if(!uniqueIPs.contains(ipAddr)) {
					//add ipAddr to uniqueIps
					uniqueIPs.add(ipAddr);
				}	
		}
		//return the size of uniqueIPs
		return uniqueIPs.size();
	}
	
	public void printAllHigherThanNum(int num) {
		System.out.println("\nStatus code higher than "+num);
		for(LogEntry le: records) {
			int statusCode = le.getStatusCode();
			if(statusCode > num) {
				System.out.println(le);
			}
		}
	}
	
	public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
		System.out.println("\nGetting unique IP visits for "+ someday);
		ArrayList<String> ipAddr = new ArrayList<String>();
		for (LogEntry le: records) {
			//System.out.println(le);
			//System.out.println(le.getAccessTime().toString().substring(4,10));
			if(someday.equals(le.getAccessTime().toString().substring(4,10)) && !ipAddr.contains(le.getIpAddress())) {
				ipAddr.add(le.getIpAddress().toString());
			}
		}
		return ipAddr;
	}
	
	public int countUniqueIPsInRange(int low, int high) {
		ArrayList<String> ipAddr = new ArrayList<String>();
		for (LogEntry le: records) {
			int statusCode = le.getStatusCode();
			if(!ipAddr.contains(le.getIpAddress()) && statusCode >= low && statusCode <= high) {
				ipAddr.add(le.getIpAddress());
			}
		}
		return ipAddr.size();
	}
	
	public HashMap<String, Integer> countVisitsPerIP() {
		//Make an empty HashMap<String, Integer> counts
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		//For each le in records
		for(LogEntry le: records) {
			//ip is le's ipAddress
			String ipAddr = le.getIpAddress();
			//check if ip is in counts
			if(!counts.containsKey(ipAddr)) {
				//if not: put in up in with a value of 1
				counts.put(ipAddr, 1);
			}
			else {
				//if so: update value by 1
				counts.put(ipAddr, counts.get(ipAddr)+1);
			}
		}
		//counts is the answer
		return counts;
	}
	
	public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
		int maxNo = 0;
		for(String s: counts.keySet()) {
			if(counts.get(s) > maxNo) {
				maxNo = counts.get(s);
			}
		}
		return maxNo;
	}
	
	public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
		ArrayList<String> ipAddr = new ArrayList<String>();
		int maxNoVisits = mostNumberVisitsByIP(counts);
		for(String s: counts.keySet()) {
			if(counts.get(s) == maxNoVisits) {
				ipAddr.add(s);
			}
		}
		return ipAddr;
	}
	
	public HashMap<String, ArrayList<String>> iPsForDays() {
		HashMap<String, ArrayList<String>> dates = new HashMap<String, ArrayList<String>>();
		for(LogEntry le: records) {
			String dateF = le.getAccessTime().toString().substring(4,10);
			ArrayList<String> ipAddr = new ArrayList<String>();
			if(!dates.containsKey(dateF)) {
				ipAddr.add(le.getIpAddress());
				dates.put(dateF,ipAddr);
			}
			else {
				ipAddr = dates.get(dateF);
				ipAddr.add(le.getIpAddress());
				dates.put(dateF,ipAddr);
			}
		}
		return dates;
	}
	
	public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dates) {
		//int size = 0;
		int maxSize = 0;
		String mostIPs = "";
		for(String s: dates.keySet()) {
			ArrayList<String> ipAddr = dates.get(s);
			//size = ipAddr.size();
			if(ipAddr.size()>maxSize) {
				maxSize = ipAddr.size();
				mostIPs = s;
			}
		}
		return mostIPs;
	}
	
	public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dates, String dateF) {
		ArrayList<String> ipListForDateF = dates.get(dateF);
		HashMap<String, ArrayList<String>> datesDateF = new HashMap<String, ArrayList<String>>();
		datesDateF.put(dateF, ipListForDateF);
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		for(String s: ipListForDateF) {
			if(!counts.containsKey(s)) {
				counts.put(s, 1);
			}
			else {
				counts.put(s, counts.get(s)+1);
			}
		}
		return iPsMostVisits(counts);
	}

}
