package Utilities;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MortUtils {
    
    private double interestRate = 0;
    private double monthlyPayment = 0;
    private double principal = 0;
    private int term = 0;
    private String errorMsg = "";
    private double interestPaid = 0;

    
    public MortUtils(HttpServletRequest request, HttpServletResponse response){
        
        try {
            interestRate = Double.parseDouble(request.getParameter("interest"));
        } catch (NumberFormatException e) {
            errorMsg = "**Invalid input.";
        }
        try {
            monthlyPayment = Double.parseDouble(request.getParameter("payment"));
        } catch (NumberFormatException e) {
            errorMsg = "**Invalid input.";
        }
        try { 
            principal = Double.parseDouble(request.getParameter("principal"));
        } catch (NumberFormatException e) {
            errorMsg = "**Invalid input.";
        }
        try { 
            term = Integer.parseInt(request.getParameter("term"));
        } catch (NumberFormatException e) {
            errorMsg = "**Invalid input.";
        }
        
    }
    
    public String doIt(){
        
       // try (PrintWriter out = response.getWriter()) {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("<font color=\"FF0000\">" + this.errorMsg + "</font>");
        if(errorMsg.equals("")){
            sb.append("<table><tr><td>Month Number"
                + "</td><td>Principal</td><td>Interest</td>");
        
                for (int i = 1; i <= term*12; i++){ 
                interestPaid = (principal * interestRate) / (12*100);
                principal += interestPaid;
                if (principal - monthlyPayment < 0){
                    break;
                }
                principal -= monthlyPayment;
                sb.append("<tr><td>" + i + "</td>");
                sb.append("<td>$" + String.format("%.2f", principal)/*principal*/ + "</td>");
                sb.append("<td>$" + String.format("%.2f", interestPaid) + "</td></tr>\n");
                
            }
            sb.append("</table>\nLast Payment = $" + String.format("%.2f", principal) + 
                    "</br>With final interest being = $" + String.format("%.2f", interestPaid));
        }
        
        sb.append("<form method='post' action='mortgageIndex.html'><input type='submit' value='Go back'></form>");
        
        return sb.toString();
                
    }
    
}
