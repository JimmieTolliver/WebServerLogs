import java.util.*;

public class Tester {
	
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
    	String filename = "/home/jimmie/links/WebServerLogs/WebLogProgram/weblog2_log";
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        //la.printAll();
        //System.out.println(la.countUniqueIPs() + " unique IP addresses.");
        //la.printAllHigherThanNum(400);
        //System.out.println(la.uniqueIPVisitsOnDay("Sep 27"));
        //System.out.println("\n"+la.countUniqueIPsInRange(400, 499));
        
        //System.out.println("\n"+la.countVisitsPerIP());
        //System.out.println("\n"+la.mostNumberVisitsByIP(la.countVisitsPerIP()));
        //System.out.println("\n"+la.iPsMostVisits(la.countVisitsPerIP()));
        System.out.println("\n"+la.iPsForDays());
        //System.out.println("\n"+la.dayWithMostIPVisits(la.iPsForDays()));
        //System.out.println("\n"+la.iPsWithMostVisitsOnDay(la.iPsForDays(), la.dayWithMostIPVisits(la.iPsForDays())));
        System.out.println("\n"+la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tester test = new Tester();
		test.testLogAnalyzer();
	}

}
