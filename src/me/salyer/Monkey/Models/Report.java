package me.salyer.Monkey.Models;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Report
{
    private String report;

    public static final Integer ENTRY_HEIGHT = 4;

    public String getAccountNumbers()
    {
        return report;
    }

    public Report(String fileName)
    {
        this.report = "";
        open(fileName);
    }

    public void print()
    {
        System.out.print(report);
    }

    private void open(String fileName)
    {
        FileInputStream fStream;
        DataInputStream dStream;
        BufferedReader reader = null;

        // this seems ugly to me
        try
        {
            fStream = new FileInputStream(fileName);
            dStream = new DataInputStream(fStream);
            reader = new BufferedReader(new InputStreamReader(dStream));

            try
            {
                process(reader);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

    }

    private void process(BufferedReader reader) throws IOException
    {
        String rawReport = "";
        List<String> lines = new ArrayList<String>();

        int i = 1;

        String line;
        while ((line = reader.readLine()) != null)
        {
            lines.add(line);
            if ( i % ENTRY_HEIGHT == 0 )
            {
                Account a = new Account(lines.get(i - 4), lines.get(i - 3),
                        lines.get(i - 2));
                rawReport += a.getAccountNumber() + "\n";
            }
            i++;
        }
        
        this.report = rawReport;
    }

}
