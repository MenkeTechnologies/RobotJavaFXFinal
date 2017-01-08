package ServerApplication;

import RobotUtilities.RunBashCommands;
import RobotUtilities.RunPythonScripts;
import gui_main.JacobsRobot;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileServer extends EchoServer {
    StringTokenizer parseCommand;
    PrintStream outputPs = null;
    JacobsRobot gui;

    FileServer(JacobsRobot gui) {
        this.gui = gui;
    }

    public void delete() {
        File f = getFile();
        if (f != null) {
            outputPs.println("deleting " + f.getAbsolutePath());
            if (f.delete())
                outputPs.println(" Successful delete");
            else
                outputPs.println(" unSuccessful delete");
        }
    }

    public void rename() {
        File f = getFile();
        if (f != null) {
            outputPs.println("renaming " + f.getAbsolutePath());
            File f2 = getFile();
            if (f.renameTo(f2))
                outputPs.println(" Successful rename");
            else
                outputPs.println(" unSuccessful rename");
        }
    }

    public void list() {
        File f = getFile();
        if (f != null) {
            outputPs.println("Listing files for " + f.getAbsolutePath());
            if (f.exists()) {
                String[] files = f.list();
                if (files != null) {
                    for (int i = 0; i < files.length; i++)
                        outputPs.println(files[i]);
                }
            } else
                outputPs.println("list error -  Non-existent:" + f.getAbsolutePath());
        }
    }

    public void size() {
        File f = getFile();
        if (f != null) {
            if (f.exists()) {
                outputPs.print("size for " + f.getAbsolutePath());
                long len = f.length();
                outputPs.println(" is = " + len);
            } else
                outputPs.println("size error -  Non-existent:" + f.getAbsolutePath());
        }
    }

    public void lastModified() {
        File f = getFile();
        if (f != null) {
            if (f.exists()) {
                outputPs.println("lastModified for " + f.getAbsolutePath());
                long date = f.lastModified();
                outputPs.println(" date=" + new Date(date));
            } else
                outputPs.println("lastModified error Non-existent:" + f.getAbsolutePath());
        }
    }

    public void mkdir() {
        File f = getFile();
        if (f != null) {
            if (f.mkdir())
                outputPs.println("mkdir successful: " + f.getAbsolutePath());
            else
                outputPs.println("mkdir unsuccessful: " + f.getAbsolutePath());
        }
    }

    public void createFile() {
        File f = getFile();
        if (f != null) {
            try {
                PrintWriter pw = new PrintWriter(f);
                String token = getNextToken();
                while (token != null) {
                    pw.println(token);
                    token = getNextToken();
                }
                pw.close();
                outputPs.println("created file for " + f.getAbsolutePath());
            } catch (FileNotFoundException e) {
                outputPs.println("createFile can't create: " + f.getAbsolutePath());
            }
        }
    }

    public void printFile() {
        File f = getFile();
        if (f != null) {
            try {
                Scanner scan = new Scanner(f);
                while (scan.hasNextLine()) {
                    outputPs.println(scan.nextLine());
                }
                scan.close();
                outputPs.println("printed file for " + f.getAbsolutePath());
            } catch (FileNotFoundException e) {
                outputPs.println("printFile can't open: " + f.getAbsolutePath());
            }
        }
    }

    void printUsage() {
        outputPs.println("?");
        outputPs.println("saf go to google");
        outputPs.println("quit");
        outputPs.println("delete filename");
        outputPs.println("rename oldFilename newFilename");
        outputPs.println("size filename");
        outputPs.println("lastModified filename");
        outputPs.println("list dir");
        outputPs.println("printFile filename");
        outputPs.println("createFile filename <remaining tokens written to file>");
        outputPs.println("mkdir dir");
    }

    private String getNextToken() {
        if (parseCommand.hasMoreTokens())
            return parseCommand.nextToken();
        else
            return null;
    }

    public File getFile() {
        File f = null;
        String fileName = getNextToken();
        if (fileName == null)
            outputPs.println("Missing a File name");
        else
            f = new File(fileName);

        return f;
    }

    public boolean processCommandLine(String line) {

        if (line == null)
            return false;

        boolean retval = true;
        parseCommand = new StringTokenizer(line);
        String cmd = getNextToken();
        if (cmd == null) {
            outputPs.println("No command specified");
        } else {
            switch (cmd) {
                case "?":
                    printUsage();
                    break;
                case "hello":
                    RunBashCommands.greetings("Hello. My name is Daniel!!");
                    break;
                case "off":
                    RunPythonScripts.runPythonScriptServerDontDeleteKeys(gui.python3Path + " /Users/jacobmenke/PycharmProjects/shutdown.py");
                    break;
                case "saf":
                    RunPythonScripts.runPythonScriptServerDontDeleteKeys("python /Users/jacobmenke/PycharmProjects/googleSearch.py");
                    break;
                case "quit":
                    retval = false;
                    break;
                case "delete":
                    delete();
                    break;
                case "rename":
                    rename();
                    break;
                case "size":
                    size();
                    break;
                case "lastModified":
                    lastModified();
                    break;
                case "list":
                    list();
                    break;
                case "printFile":
                    printFile();
                    break;
                case "createFile":
                    createFile();
                    break;
                case "mkdir":
                    mkdir();
                    break;
                default:
                    outputPs.println("Unrecognized command: " + cmd);
                    break;
            }
        }

        outputPs.println("");

        return retval;
    }

    public void processStream(InputStream is, OutputStream os) {

        System.out.println("HttpServer.processStream begins");

        outputPs = new PrintStream(os);
        BufferedReader input = new BufferedReader(new InputStreamReader(is));

        String cmd;
        try {
            // We are only looking at GET requests:
            // We will be looking for something like:
            // GET /like.html HTTP/1.1

            cmd = input.readLine(); // We are only looking at the first line
            System.out.println(cmd);
            StringTokenizer parse = null;
            if (cmd != null)
                parse = new StringTokenizer(cmd);
            String command = "";
            if (parse != null && parse.countTokens() >= 2) {
                String method = parse.nextToken();  // Should contain GET
                String tempFileName = parse.nextToken().trim();         // Could contain a filename
                // We expect either "/"  or "/someFileName"
                if (!tempFileName.equals("/"))
                    command = tempFileName.substring(1); // strip off leading "/"

                System.out.println("method=" + method);
                System.out.println("command=" + command);
            }

            outputPs.println("HTTP/1.1 200 OK");
            outputPs.println("content-type: text/html");

// This blank line says we are done with HTTP headers and ready to send the data
            outputPs.println();

            processCommandLine(command);

            outputPs.println(command);
            outputPs.println("<hr/>");
            outputPs.println("<strong>" + command + "</strong>");

// Send out our html file to the browser
            File f = new File(System.getProperty("user.home") + "/IdeaProjects/myRobot/gui_main.test.html");
            System.out.println(f.getAbsolutePath());

            Scanner readIt = new Scanner(f);
            while (readIt.hasNextLine()) {
                String s = readIt.nextLine();
                outputPs.println(s);
            }
// closing the TCP/IP connection tells the browser we are done.
            input.close();
            outputPs.close();
            is.close();
            os.close();
        } catch (IOException e) {
            System.out.println("HttpServer.processStream IOException: " + e);
        }
        System.out.println("Exitting processStream");
    }

//    public static void main(String[] args) {
//        ServerApplication.FileServer fs = new ServerApplication.FileServer();
//        fs.monitorServer();
//        System.out.println("Exitting ServerApplication.FileServer");
//    }
}