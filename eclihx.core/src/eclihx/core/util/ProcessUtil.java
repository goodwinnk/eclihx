package eclihx.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.eclipse.debug.core.DebugPlugin;

import eclihx.core.EclihxCore;

/**
 * This class contains utility method for processes.
 */
public class ProcessUtil {

    /** 
     * The size of the read buffers. 
     */
    private static final int BUFFER_SIZE = 256;
    
    
    /** 
     * Class isn't supposed to be instantiated. 
     * */
    private ProcessUtil() { }
    
    
    /**
     * Returns the command line in order to start a process.
     * 
     * Options are given as a matrix of n rows and 2 columns (the option name 
     * and the value). The value may be null or empty.
     * @param program the name of the program
     * @param options the options of the program (they may have values)
     * @param projectOptions the project options
     * @param arguments the arguments of the program
     * @return the command line
     */
    /*public static CommandLine getCommandLine(String program, List<Pair<String, String>> options, 
                                             Set<ProjectOption> projectOptions, 
                                             String[] arguments) {
        CommandLine commandLine = new CommandLine(program);
        
        // the options
        try {
            if ((options != null) && (options.size() > 0)) {
                Iterator<Pair<String, String>> optionsIterator = options.iterator();
                while (optionsIterator.hasNext()) {
                    Pair<String, String> option = optionsIterator.next();
                    String optionName = option.first();
                    String optionValue = option.second();
                    
                    if ((optionName != null) && (optionName.length() > 0)) {
                        commandLine.addArgument(optionName);
                        
                        if ((optionValue != null) && (optionValue.length() > 0)) {
                            commandLine.addArgument(optionValue);
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
            
        // the project options
        if (projectOptions != null) {
            Iterator<ProjectOption> projectOptionsIterator = projectOptions.iterator();
                
            while (projectOptionsIterator.hasNext()) {
                ProjectOption projectOption = projectOptionsIterator.next();

                projectOption.addToCommandLine(commandLine);
            }
        }
            
        // the arguments
        if ((arguments != null) && (arguments.length > 0)) {
            for (int argumentIndex = 0 ; argumentIndex < arguments.length ; argumentIndex++) {
                String argument = arguments[argumentIndex];
                
                if ((argument != null) && (argument.length() > 0)) {
                    commandLine.addArgument(argument);
                }
            }
        }
        
        return commandLine;
    }*/
    
    /**
     * Returns the content of the process output.
     * @param inputStream the stream that contains the process output
     * @param outputStreams the streams to which output is written
     * @return the process output string
     */
    public static String getProcessOutput(
    		InputStream inputStream, 
    		OutputStream[] outputStreams) {
    	
        StringBuffer processOutput = new StringBuffer();
        
        byte[] buffer = new byte[BUFFER_SIZE];
        int byteRead = 0;
        
        try {
            while (true) {
                byteRead = inputStream.read(buffer);
                
                if (byteRead == -1) {
                    break;
                } else {
                    byte[] realOutput = new byte[byteRead];
                    System.arraycopy(buffer, 0, realOutput, 0, byteRead);
                    
                    for (int streamIndex = 0 ; 
                    	 streamIndex < outputStreams.length ; 
                    	 streamIndex++) {
                        OutputStream stream = outputStreams[streamIndex];
                        stream.write(realOutput);
                    }
                    
                    String output = new String(realOutput);
                    processOutput.append(output);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return processOutput.toString();
    }
    
    /**
     * Executes the command line at the given project location, and returns the 
     * corresponding process.
     * @param commandLine the command line
     * @param project the project in which the command line is executed
     * @return the corresponding process
     * @throws IOException if the command line can't be executed
     */
    public static Process launchProcess(
    		String commandLine, File outputDirectory) 
    	throws IOException {
    	
        String fullCommandLine = commandLine + ' ' + outputDirectory.getAbsolutePath();
           
        Process process = Runtime.getRuntime().exec(
        	DebugPlugin.parseArguments(commandLine), null, outputDirectory);
        
        return process;
    }
    
    /**
     * Executes the command line at the given project location, and returns the 
     * corresponding ouput.
     * 
     * The error ouput is placed before the standard output.
     * @param commandLine the command line
     * @param project the project in which the command line is executed
     * @return the process output
     */
    public static void executeProcess(
    		String commandLine, File outputDirectory,
    		final StringBuilder errorString, final StringBuilder outputString) {
    	
    	EclihxCore.getLogHelper().logInfo(commandLine);
    	
        /*
        try {
            // Execute a command
            String command = "ls";
            Process child = Runtime.getRuntime().exec(command);
        
            // Read from an input stream
            InputStream in = child.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                process((char)c);
            }
            in.close();
        } catch (IOException e) {
        }*/
    	Process process;
    	
		try {
			process = launchProcess(commandLine, outputDirectory);
		} catch (IOException e1) {
			return;
		}
   
        
        final BufferedReader processErrorReader = 
        	new BufferedReader(
        		new InputStreamReader(
        			new BufferedInputStream(
        				process.getErrorStream()
        			)
        		)
        	);
        
        final BufferedReader processOutputReader = 
        	new BufferedReader(
        		new InputStreamReader(
        			new BufferedInputStream(
        				process.getInputStream()
        			)
        		)
        	);
        
        Thread errorReaderThread = new Thread() {

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				try {
					while (true) {
						String line = processErrorReader.readLine();
						
						if (line != null) {
							errorString.append(line);
						} else {
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
        	
        };
        
        Thread outputReaderThread = new Thread() {

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				try {
					while (true) {
						String line = processOutputReader.readLine();
						
						if (line != null) {
							outputString.append(line);
						} else {
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
        	
        };
        
        outputReaderThread.start();
        errorReaderThread.start();
        
        try {
        	outputReaderThread.join();
			errorReaderThread.join();
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Executes the command line at the given project location, and returns the 
     * corresponding ouput.
     * 
     * The error ouput is placed before the standard output.
     * @param commandLine the command line
     * @param project the project in which the command line is executed
     * @param standardStreams the streams to which standard output is written 
     * @param errorStreams the streams to which error output is written 
     * @return the process output
     */
    public static String executeProcess(String commandLine,
    									Process process, 
                                        final OutputStream[] standardStreams, 
                                        final OutputStream[] errorStreams) {
        try {
            for (int streamIndex = 0 ; streamIndex < standardStreams.length ; 
            	streamIndex++) {
                
            	OutputStream standardStream = standardStreams[streamIndex];
                OutputStreamWriter writer = 
                	new OutputStreamWriter(standardStream);
                writer.write(commandLine.toString());
                writer.write('\n');
                writer.flush();
            }
            
            // Getting old streams
            final InputStream outputStream = process.getInputStream();
            final InputStream errorStream = process.getErrorStream();
            
            StringBuffer processOutput = new StringBuffer();

            final StringBuffer standardOutput = new StringBuffer();
            final StringBuffer errorOutput = new StringBuffer();
            
            Runnable getStandardOutput = new Runnable(){
                public void run() {
                    String output = getProcessOutput(outputStream, standardStreams);
                    standardOutput.append(output);
                }};
            Runnable getErrorOutput = new Runnable(){
                public void run() {
                    String output = getProcessOutput(errorStream, errorStreams); 
                    errorOutput.append(output);
                }};
            Thread getStandardOutputThread = new Thread(getStandardOutput);
            Thread getErrorOutputThread = new Thread(getErrorOutput);

            getStandardOutputThread.start();
            getErrorOutputThread.start();
            Thread.yield();
            
            try {
                getStandardOutputThread.join();
            } catch (InterruptedException e) {}
            try {
                getErrorOutputThread.join();
            } catch (InterruptedException e) {}
            
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            processOutput.append(errorOutput);
            processOutput.append(standardOutput);
            
            return processOutput.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new String();
    }
}
