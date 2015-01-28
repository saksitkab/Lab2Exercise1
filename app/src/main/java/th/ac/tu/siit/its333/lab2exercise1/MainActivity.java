package th.ac.tu.siit.its333.lab2exercise1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // expr = the current string to be calculated
    StringBuffer expr;
    int memory = 0;
    int finalanswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expr = new StringBuffer();
        updateExprDisplay();
    }

    public void updateExprDisplay() {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(expr.toString());
    }

    public void updateAnsDisplay(String ans){
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(ans);
    }
    //To get the last character
    //expr.charAt(expr.length()-1)
    public void recalculate() {
        //Calculate the expression and display the output
        //Split expr into numbers and operators
        //e.g. 123+45/3 --> ["123", "+", "45", "/", "3"]
        //reference: http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String e = expr.toString();
        String[] tokens = e.split("((?<=\\+)|(?=\\+))|((?<=\\-)|(?=\\-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");
        int i ;
        int answer = 0;
        String op = "+";
        for(i=0;i<tokens.length;i++)
        {
            //Log.d("i", i+"");
            if(i%2 == 0)
            {
                int n = Integer.parseInt(tokens[i]);
                //Log.d("Op2", op);
                if(op.equals("+"))
                {
                    //Log.d("Op", "ADD!");
                    answer = answer + n;
                }
                else if(op.equals("-"))
                {
                    answer = answer - n;
                }
                else if(op.equals("*"))
                {
                    answer = answer * n;
                }
                else if(op.equals("/"))
                {
                    answer = answer / n;
                }
            }
            else if (i%2 != 0)
            {
                op = tokens[i];
                //Log.d("Op", "["+op+"]");
            }

        }

        updateAnsDisplay(Integer.toString(answer));
        finalanswer = answer ;

    }

    public void equalClicked(View v) {

        if(expr.length() == 0 )
        {
            Toast tr = Toast.makeText(this.getApplicationContext(),"Enter something" , Toast.LENGTH_LONG);
            tr.show();
        }
        else if (expr.length() != 0)
        {
            expr = new StringBuffer();
            expr.append(finalanswer);
            updateExprDisplay();
            updateAnsDisplay("0");
        }

    }

    public void digitClicked(View v) {
        //d = the label of the digit button
        String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        expr.append(d);
        //update tvExpr
        updateExprDisplay();
        //calculate the result if possible and update tvAns
        recalculate();
    }

    public void operatorClicked(View v) {
        //IF the last character in expr is not an operator and expr is not "",
        // sub , div ,mul ,add
        //THEN append the clicked operator and updateExprDisplay,
        //ELSE do nothing
        // Check expr
        String stream = expr.toString();
        int i;

        if(stream.isEmpty())
        {
            Toast t = Toast.makeText(this.getApplicationContext(), "Please enter some number first" , Toast.LENGTH_SHORT);
            t.show();
        }
        else if(stream.charAt(expr.length()-1) != '+' && stream.charAt(expr.length()-1) != '-' && stream.charAt(expr.length()-1) != '*' && stream.charAt(expr.length()-1) != '/')
        {
            String d = ((TextView)v).getText().toString();
            expr.append(d);
            updateExprDisplay();
            //recalculate();
        }
        else
        {
            Toast t = Toast.makeText(this.getApplicationContext(), "Error" , Toast.LENGTH_SHORT);
            t.show();
        }
        //

    }

    public void ACClicked(View v) {
        //Clear expr and updateExprDisplay
        expr = new StringBuffer();
        updateExprDisplay();
        //Display a toast that the value is cleared
        Toast t = Toast.makeText(this.getApplicationContext(),
                "All cleared", Toast.LENGTH_SHORT);
        t.show();
    }

    public void BSClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        if (expr.length() > 0) {
            expr.deleteCharAt(expr.length()-1);
            updateExprDisplay();
            recalculate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void memAdd(View v)
    {
        TextView TvAns = (TextView)findViewById(R.id.tvAns);
        memory += Integer.parseInt(TvAns.getText().toString());
        updateExprDisplay();
        Toast t = Toast.makeText(this.getApplicationContext(), Integer.toString(memory) , Toast.LENGTH_SHORT);
        t.show();
        recalculate();
    }

    public void memSub(View v)
    {
        TextView TvAns = (TextView)findViewById(R.id.tvAns);
        memory -= Integer.parseInt(TvAns.getText().toString());
        updateExprDisplay();
        Toast t = Toast.makeText(this.getApplicationContext(), Integer.toString(memory) , Toast.LENGTH_SHORT);
        t.show();
        recalculate();
    }

    public void memClr(View v)
    {
        //String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        memory = 0;
        updateExprDisplay();
        Toast t = Toast.makeText(this.getApplicationContext(), Integer.toString(memory) , Toast.LENGTH_SHORT);
        t.show();

        recalculate();
    }

    public void memAppen(View v)
    {
        expr = new StringBuffer(Integer.toString(memory));
        updateExprDisplay();
        Toast t = Toast.makeText(this.getApplicationContext(), Integer.toString(memory) , Toast.LENGTH_SHORT);
        t.show();
        recalculate();
    }
}