package me.salyer.Monkey.Models;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public Report(String fileName) throws IOException
    {
        this.report = "";
        open(fileName);
    }

    public void print()
    {
        System.out.print(report);
    }

    private void open(String fileName) throws IOException
    {
        FileInputStream fStream = fileStream(fileName);

        DataInputStream dStream = new DataInputStream(fStream);

        BufferedReader reader = new BufferedReader(new InputStreamReader(dStream));

        process(reader);

        reader.close();

    }

    private FileInputStream fileStream(String fileName) throws FileNotFoundException
    {
        FileInputStream fStream = new FileInputStream(fileName);

        return fStream;
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
