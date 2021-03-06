import java.io.File;
import java.util.Scanner;

public class ExportFromParatext {
    public static void main(String[] args) {
        for(int i=0; i < args.length; i++) {        
            String reference = "";
            String bookName = "";
            String bookAbbreviation = "";
            String bookNumber = "";
            String chapterNumber = "";
            String beginningVerse = "";
            String endingVerse = "";
            boolean finishedBookName = false;
            boolean finishedChapter = false;
            boolean foundDash = false;
            boolean validReference = true;
            String invalidReason = "";
            String projectName = "MP1"; //todo: Move to a config file
            String fileName = "";
            String installPath = "C:\\My Paratext 8 Projects\\"; //todo: move to a config file
            
            reference = args[i];
            
            //parse the reference
            for (int j = 0; j < reference.length(); j++) {
                //get the book name
                if (!finishedBookName) {
                    if (reference.charAt(j) == ' ' 
                            && !bookName.toUpperCase().equals("SONG") //TODO: also add condition for "Song 1:1" etc
                            && !bookName.toUpperCase().equals("SONG OF")
                            && !bookName.equals("1") 
                            && !bookName.equals("2") 
                            && !bookName.equals("1st") 
                            && !bookName.equals("2nd") 
                            && !bookName.equals("First")
                            && !bookName.equals("Second"))
                        finishedBookName = true;
                    else 
                        bookName = bookName + reference.charAt(j);
                }
                //get the chapter number
                if (finishedBookName && !finishedChapter && reference.charAt(j) != ' ') {
                    if (reference.charAt(j) == ':')
                        finishedChapter = true;
                    else
                        chapterNumber = chapterNumber + reference.charAt(j);
                }
                //get the verse before the dash
                if (finishedBookName && finishedChapter && !foundDash && reference.charAt(j) != ':') {
                    if (reference.charAt(j) == '-') {
                        foundDash = true;
                    }
                    else
                        beginningVerse = beginningVerse + reference.charAt(j);
                }
                //get the verse after the dash
                if (finishedBookName && finishedChapter && foundDash && reference.charAt(j) != '-') {
                    endingVerse = endingVerse + reference.charAt(j);
                }
            } //end parsing if
            
            if (endingVerse.length() == 0)
                endingVerse = beginningVerse;
			
			if (Integer.parseInt(endingVerse) < Integer.parseInt(beginningVerse)) {
				validReference = false;
				invalidReason += "The ending verse is less than the beginning verse.";
			}
            if (bookName.length() == 0) {
				validReference = false;
                invalidReason += "Unable to identify book. ";
			}
            if (chapterNumber.length() == 0) {
				validReference = false;
                invalidReason += "Unable to identify chapter number. ";
			}
            if (beginningVerse.length() == 0) {
				validReference = false;
                invalidReason += "Unable to identify verse. ";
			}
            
            //map the book name to the abbreviation - these abbreviations and numbering are the Paratext standard
            switch (bookName.toUpperCase()) {
                /* Old Testament */
                case "GEN": bookAbbreviation = "GEN"; bookNumber = "01"; break;
                case "GENESIS": bookAbbreviation = "GEN"; bookNumber = "01"; break;
                case "GENE": bookAbbreviation = "GEN"; bookNumber = "01"; break;
                case "GENES": bookAbbreviation = "GEN"; bookNumber = "01"; break;
                case "EXO":  bookAbbreviation = "EXO"; bookNumber = "02"; break;
                case "EXODUS":  bookAbbreviation = "EXO"; bookNumber = "02"; break;
                case "EXOD":  bookAbbreviation = "EXO"; bookNumber = "02"; break;
                case "LEV":  bookAbbreviation = "LEV"; bookNumber = "03"; break;
                case "LEVITICUS":  bookAbbreviation = "LEV"; bookNumber = "03"; break;
                case "LEVI":  bookAbbreviation = "LEV"; bookNumber = "03"; break;
                case "LEVIT":  bookAbbreviation = "LEV"; bookNumber = "03"; break;
                case "LEVETICUS":  bookAbbreviation = "LEV"; bookNumber = "03"; break;
                case "NUM":  bookAbbreviation = "NUM"; bookNumber = "04"; break;
                case "NUMBERS":  bookAbbreviation = "NUM"; bookNumber = "04"; break;
                case "NUMB":  bookAbbreviation = "NUM"; bookNumber = "04"; break;
                case "NUMBER":  bookAbbreviation = "NUM"; bookNumber = "04"; break;
                case "NMBR":  bookAbbreviation = "NUM"; bookNumber = "04"; break;
                case "NMBRS":  bookAbbreviation = "NUM"; bookNumber = "04"; break;
                case "DEU":  bookAbbreviation = "DEU"; bookNumber = "05"; break;
                case "DUE": bookAbbreviation = "DEU"; bookNumber = "05"; break;
                case "DEUTERONOMY":  bookAbbreviation = "DEU"; bookNumber = "05"; break;
                case "DUETERONOMY":  bookAbbreviation = "DEU"; bookNumber = "05"; break;
                case "DEUT":  bookAbbreviation = "DEU"; bookNumber = "05"; break;
                case "DUET":  bookAbbreviation = "DEU"; bookNumber = "05"; break;
                case "DEUTER":  bookAbbreviation = "DEU"; bookNumber = "05"; break;
                case "DUETER":  bookAbbreviation = "DEU"; bookNumber = "05"; break;
                case "JOS":  bookAbbreviation = "JOS"; bookNumber = "06"; break;
                case "JOSH":  bookAbbreviation = "JOS"; bookNumber = "06"; break;
                case "JOSHUA":  bookAbbreviation = "JOS"; bookNumber = "06"; break;
                case "JDG":  bookAbbreviation = "JDG"; bookNumber = "07"; break;
                case "JUDGES":  bookAbbreviation = "JDG"; bookNumber = "07"; break;
                case "JUDG":  bookAbbreviation = "JDG"; bookNumber = "07"; break;
                case "JUDGE":  bookAbbreviation = "JDG"; bookNumber = "07"; break;
                case "JGS":  bookAbbreviation = "JDG"; bookNumber = "07"; break;
                case "RUT":  bookAbbreviation = "RUT"; bookNumber = "08"; break;
                case "RUTH":  bookAbbreviation = "RUT"; bookNumber = "08"; break;
                case "RU":  bookAbbreviation = "RUT"; bookNumber = "08"; break; 
                case "1 SAMUEL": bookAbbreviation = "1SA"; bookNumber = "09"; break;
                case "1SA":  bookAbbreviation = "1SA"; bookNumber = "09"; break;
                case "1 SA":  bookAbbreviation = "1SA"; bookNumber = "09"; break;
                case "1 SAM":  bookAbbreviation = "1SA"; bookNumber = "09"; break;
                case "1ST SAM":  bookAbbreviation = "1SA"; bookNumber = "09"; break;
                case "1ST SAMUEL":  bookAbbreviation = "1SA"; bookNumber = "09"; break;
                case "FIRST SAMUEL": bookAbbreviation = "1SA"; bookNumber = "09"; break;            
                case "2 SAMUEL": bookAbbreviation = "2SA"; bookNumber = "10"; break;
                case "2SA":  bookAbbreviation = "2SA"; bookNumber = "10"; break;
                case "2 SA":  bookAbbreviation = "2SA"; bookNumber = "10"; break;
                case "2 SAM":  bookAbbreviation = "2SA"; bookNumber = "10"; break;
                case "2ND SAM":  bookAbbreviation = "2SA"; bookNumber = "10"; break;
                case "2ND SAMUEL":  bookAbbreviation = "2SA"; bookNumber = "10"; break;
                case "SECOND SAMUEL": bookAbbreviation = "2SA"; bookNumber = "10"; break;           
                case "1KI": bookAbbreviation = "1KI"; bookNumber = "11"; break;
                case "1 KINGS": bookAbbreviation = "1KI"; bookNumber = "11"; break;
                case "1 KING": bookAbbreviation = "1KI"; bookNumber = "11"; break;
                case "1ST KINGS": bookAbbreviation = "1KI"; bookNumber = "11"; break;
                case "1ST KING": bookAbbreviation = "1KI"; bookNumber = "11"; break;
                case "1 KI": bookAbbreviation = "1KI"; bookNumber = "11"; break;
                case "FIRST KINGS": bookAbbreviation = "1KI"; bookNumber = "11"; break;
                case "2KI":  bookAbbreviation = "2KI"; bookNumber = "12"; break;
                case "2 KINGS":  bookAbbreviation = "2KI"; bookNumber = "12"; break;
                case "2 KING":  bookAbbreviation = "2KI"; bookNumber = "12"; break;
                case "2ND KINGS":  bookAbbreviation = "2KI"; bookNumber = "12"; break;
                case "2nd KING":  bookAbbreviation = "2KI"; bookNumber = "12"; break;
                case "2 KI":  bookAbbreviation = "2KI"; bookNumber = "12"; break;
                case "SECOND KINGS":  bookAbbreviation = "2KI"; bookNumber = "12"; break;   
                case "1CH":  bookAbbreviation = "1CH"; bookNumber = "13"; break;
                case "1 CHRONICLES":  bookAbbreviation = "1CH"; bookNumber = "13"; break;       
                case "1 CHRON":  bookAbbreviation = "1CH"; bookNumber = "13"; break;    
                case "1 CH":  bookAbbreviation = "1CH"; bookNumber = "13"; break;   
                case "1ST CHRONICLES"   :  bookAbbreviation = "1CH"; bookNumber = "13"; break;              
                case "2 CHRONICLES":  bookAbbreviation = "2CH"; bookNumber = "14"; break;
                case "2CH":  bookAbbreviation = "2CH"; bookNumber = "14"; break;
                case "2ND CHRONICLES"   :  bookAbbreviation = "2CH"; bookNumber = "14"; break;  
                case "2 CHRON"  :  bookAbbreviation = "2CH"; bookNumber = "14"; break;  
                case "2 CH" :  bookAbbreviation = "2CH"; bookNumber = "14"; break;  
                case "EZRA":  bookAbbreviation = "EZR"; bookNumber = "15"; break;
                case "EZR":  bookAbbreviation = "EZR"; bookNumber = "15"; break;
                case "EZ":  bookAbbreviation = "EZR"; bookNumber = "15"; break;
                case "NEHEMIAH":  bookAbbreviation = "NEH"; bookNumber = "16"; break;
                case "NEH":  bookAbbreviation = "NEH"; bookNumber = "16"; break;
                case "NEHEM":  bookAbbreviation = "NEH"; bookNumber = "16"; break;
                case "ESTHER":  bookAbbreviation = "EST"; bookNumber = "17"; break;
                case "EST":  bookAbbreviation = "EST"; bookNumber = "17"; break;
                case "JOB":  bookAbbreviation = "JOB"; bookNumber = "18"; break;
                case "PSALMS":  bookAbbreviation = "PSA"; bookNumber = "19"; break;
                case "PSA":  bookAbbreviation = "PSA"; bookNumber = "19"; break;
                case "PS":  bookAbbreviation = "PSA"; bookNumber = "19"; break;
                case "PSALM":  bookAbbreviation = "PSA"; bookNumber = "19"; break;
                case "PROVERBS":  bookAbbreviation = "PRO"; bookNumber = "20"; break;
                case "PRO":  bookAbbreviation = "PRO"; bookNumber = "20"; break;
                case "PROV":  bookAbbreviation = "PRO"; bookNumber = "20"; break;   
                case "ECCLESIASTES":  bookAbbreviation = "ECC"; bookNumber = "21"; break;
                case "ECC":  bookAbbreviation = "ECC"; bookNumber = "21"; break;
                case "ECCLES":  bookAbbreviation = "ECC"; bookNumber = "21"; break;
                case "SONG OF SOLOMON":  bookAbbreviation = "SNG";  bookNumber = "22"; break;
                case "SONG OF SONGS":  bookAbbreviation = "SNG"; bookNumber = "22"; break;
                case "SONG": bookAbbreviation = "SNG"; bookNumber = "22"; break;
                case "SNG":  bookAbbreviation = "SNG"; bookNumber = "22"; break;
                case "Isaiah": bookAbbreviation = "ISA"; bookNumber = "23"; break;
                case "ISAIAH": bookAbbreviation = "ISA"; bookNumber = "23"; break;
                case "ISA": bookAbbreviation = "ISA"; bookNumber = "23"; break;
                case "IS": bookAbbreviation = "ISA"; bookNumber = "23"; break;
                case "JER": bookAbbreviation = "JER"; bookNumber = "24"; break;
                case "JEREMIAH": bookAbbreviation = "JER"; bookNumber = "24"; break;
                case "JEREM": bookAbbreviation = "JER"; bookNumber = "24"; break;           
                case "LAM": bookAbbreviation = "LAM"; bookNumber = "25"; break;
                case "LAMEN": bookAbbreviation = "LAM"; bookNumber = "25"; break;
                case "LAMENTATIONS": bookAbbreviation = "LAM"; bookNumber = "25"; break;
                case "LAMENT": bookAbbreviation = "LAM"; bookNumber = "25"; break;
                case "EXEKIEL": bookAbbreviation = "EZK"; bookNumber = "26"; break;
                case "EZK": bookAbbreviation = "EZK"; bookNumber = "26"; break;
                case "EZEK": bookAbbreviation = "EZK"; bookNumber = "26"; break;
                case "EZE": bookAbbreviation = "EZK"; bookNumber = "26"; break;
                case "EZEKIEL": bookAbbreviation = "EZK"; bookNumber = "26"; break;
                case "EZEKEIL": bookAbbreviation = "EZK"; bookNumber = "26"; break;
                case "DANIEL": bookAbbreviation = "DAN"; bookNumber = "27"; break;
                case "DAN": bookAbbreviation = "DAN"; bookNumber = "27"; break;
                case "DANEIL": bookAbbreviation = "DAN"; bookNumber = "27"; break;
                case "DNL": bookAbbreviation = "DAN"; bookNumber = "27"; break;             
                case "HOSEA": bookAbbreviation = "HOS"; bookNumber = "28"; break;
                case "HOS": bookAbbreviation = "HOS"; bookNumber = "28"; break;
                case "JOEL": bookAbbreviation = "JOL"; bookNumber = "29"; break;
                case "JOL": bookAbbreviation = "JOL"; bookNumber = "29"; break;
                case "JOE": bookAbbreviation = "JOL"; bookNumber = "29"; break;             
                case "AMO": bookAbbreviation = "AMO"; bookNumber = "30"; break;
                case "AMOS": bookAbbreviation = "AMO"; bookNumber = "30"; break;
                case "AMS": bookAbbreviation = "AMO"; bookNumber = "30"; break;
                case "OBA": bookAbbreviation = "OBA"; bookNumber = "31"; break;
                case "OBAD": bookAbbreviation = "OBA"; bookNumber = "31"; break;
                case "OBADIAH": bookAbbreviation = "OBA"; bookNumber = "31"; break;         
                case "JON": bookAbbreviation = "JON"; bookNumber = "32"; break;
                case "JONAH": bookAbbreviation = "JON"; bookNumber = "32"; break;
                case "MIC": bookAbbreviation = "MIC"; bookNumber = "32"; break;
                case "MICAH": bookAbbreviation = "MIC"; bookNumber = "33"; break;   
                case "MICA": bookAbbreviation = "MIC"; bookNumber = "33"; break;
                
                /* todo: add more abbreviations for nahum through 2 timothy*/
                case "NAHUM": bookAbbreviation = "NAM"; bookNumber = "34"; break;
                case "HABBAKUK": bookAbbreviation = "HAB"; bookNumber = "35"; break;
                case "ZEPHANIAH": bookAbbreviation = "ZEP"; bookNumber = "36"; break;
                case "HAGGAI": bookAbbreviation = "HAG"; bookNumber = "37"; break;
                case "ZECHARIAH": bookAbbreviation = "ZEC"; bookNumber = "38"; break;
                case "MALACHAI": bookAbbreviation = "MAL"; bookNumber = "39"; break;
                
                /* New Testament */
                case "MATTHEW": bookAbbreviation = "MAT"; bookNumber = "41"; break;
                case "MARK": bookAbbreviation = "MRK"; bookNumber = "42"; break;
                case "LUKE": bookAbbreviation = "LUK"; bookNumber = "43"; break;
                case "JOHN": bookAbbreviation = "JHN"; bookNumber = "44"; break;
                case "ACTS": bookAbbreviation = "ACT"; bookNumber = "45"; break;
                case "ROMANS": bookAbbreviation = "ROM"; bookNumber = "46"; break;
                case "1 CORINTHIANS": bookAbbreviation = "1CO"; bookNumber = "47"; break;
                case "2 CORINTHIANS": bookAbbreviation = "1CO"; bookNumber = "48"; break;
                case "GALATIANS": bookAbbreviation = "GAL"; bookNumber = "49"; break;
                case "EPHESIANS": bookAbbreviation = "EPH"; bookNumber = "50"; break;
                case "PHILIPPIANS": bookAbbreviation = "PHP"; bookNumber = "51"; break;
                case "PHILLIPIANS": bookAbbreviation = "PHP"; bookNumber = "51"; break;
                case "COLOSSIANS": bookAbbreviation = "COL"; bookNumber = "52"; break;
                case "1 THESSALONIANS": bookAbbreviation = "1TH"; bookNumber = "53"; break;
                case "2 THESSALONIANS": bookAbbreviation = "2TH"; bookNumber = "54"; break;
                case "1 TIMOTHY": bookAbbreviation = "1TI"; bookNumber = "55"; break;
                case "2 TIMOTHY": bookAbbreviation = "2TI"; bookNumber = "56"; break;   
                
                case "TITUS": bookAbbreviation = "TIT"; bookNumber = "57"; break;
                case "TIT": bookAbbreviation = "TIT"; bookNumber = "57"; break;
                case "TI": bookAbbreviation = "TIT"; bookNumber = "57"; break;
                case "PHILEMON": bookAbbreviation = "PHM"; bookNumber = "58"; break;
                case "PHIL": bookAbbreviation = "PHM"; bookNumber = "58"; break;
                case "PHI": bookAbbreviation = "PHM"; bookNumber = "58"; break;
                case "PHM": bookAbbreviation = "PHM"; bookNumber = "58"; break;
                case "PM": bookAbbreviation = "PHM"; bookNumber = "58"; break;
                case "PHILEM": bookAbbreviation = "PHM"; bookNumber = "58"; break;
                case "HEBREWS": bookAbbreviation = "HEB"; bookNumber = "59"; break;
                case "HEB": bookAbbreviation = "HEB"; bookNumber = "59"; break;
                case "HEBREW": bookAbbreviation = "HEB"; bookNumber = "59"; break;      
                case "JAMES": bookAbbreviation = "JAS"; bookNumber = "60"; break;
                case "JAM": bookAbbreviation = "JAS"; bookNumber = "60"; break;
                case "JAS": bookAbbreviation = "JAS"; bookNumber = "60"; break;
                case "1 PETER": bookAbbreviation = "1PE"; bookNumber = "61"; break;
                case "1 PET": bookAbbreviation = "1PE"; bookNumber = "61"; break;
                case "1PE": bookAbbreviation = "1PE"; bookNumber = "61"; break; 
                case "2 PET": bookAbbreviation = "2PE"; bookNumber = "61"; break;
                case "2 PETER": bookAbbreviation = "2PE"; bookNumber = "62"; break; 
                case "2PE": bookAbbreviation = "2PE"; bookNumber = "62"; break;
                case "1 JOHN": bookAbbreviation = "1JN"; bookNumber = "63"; break;
                case "1 JN": bookAbbreviation = "1JN"; bookNumber = "63"; break;
                case "1ST JOHN": bookAbbreviation = "1JN"; bookNumber = "63"; break;
                case "FIRST JOHN": bookAbbreviation = "1JN"; bookNumber = "63"; break;
                case "2 JOHN": bookAbbreviation = "2JN"; bookNumber = "64"; break;
                case "2 JN": bookAbbreviation = "2JN"; bookNumber = "64"; break;
                case "2ND JOHN": bookAbbreviation = "2JN"; bookNumber = "64"; break;
                case "SECOND JOHN": bookAbbreviation = "2JN"; bookNumber = "64"; break;
                case "3 JOHN": bookAbbreviation = "3JN"; bookNumber = "65"; break;
                case "3 JN": bookAbbreviation = "3JN"; bookNumber = "65"; break;
                case "3RD JOHN": bookAbbreviation = "3JN"; bookNumber = "65"; break;
                case "THIRD JOHN": bookAbbreviation = "3JN"; bookNumber = "65"; break;
                case "JUDE": bookAbbreviation = "JUD"; bookNumber = "66"; break;
                case "JU": bookAbbreviation = "JUD"; bookNumber = "66"; break;
                case "JUD": bookAbbreviation = "JUD"; bookNumber = "66"; break;
                case "REVELATION": bookAbbreviation = "REV"; bookNumber = "67"; break;
                case "REV": bookAbbreviation = "REV"; bookNumber = "67"; break;
                case "REVELATIONS": bookAbbreviation = "REV"; bookNumber = "67"; break;
                case "REVEL": bookAbbreviation = "REV"; bookNumber = "67"; break;
                     
                default: validReference = false; invalidReason += "Unable to map book name to its abbreviation. ";
                         break;
            } //end abbreviation switch
            
            int invalidity = invalidReason.length();
			invalidReason = validateChapter(invalidReason, bookAbbreviation, chapterNumber);
			if (invalidReason.length() > invalidity) //the validation added an error
				validReference = false;
            
            if (validReference) {
                fileName = bookNumber + bookAbbreviation + projectName + ".SFM";
                
                //String nextChapter = Integer.toString((Integer.parseInt(chapterNumber) + 1));
                //System.out.println("Next chapter:" + nextChapter);
                
                //check if file exists
                File expectedFile = new File(installPath + projectName + "/" + fileName);
                boolean fileExists = expectedFile.exists();
                
                //walk through the file
                if (fileExists) {
                    try {
                        Scanner scanner = new Scanner(expectedFile, "UTF-8");
                        String currentLine;
                        boolean foundChapter = false;
                        boolean foundStartVerse = false;
						boolean foundEndVerse = false;
                        boolean foundMarkupTag = false;
                        String currentTag = "";
                        boolean tagFound = false;
                        String tagText = "";
                        String resultText = "";
                        
                        while (scanner.hasNextLine()) {
                            currentLine = scanner.nextLine();
							boolean currentLinePrinted = false;
                            
                            if (currentLine.equals("\\c " + chapterNumber))
                                foundChapter = true;
                            if (foundChapter && !foundEndVerse) { //todo: add case for verses included in a span in paratext (eg, the verse I am seeking is not explicitly tagged)
                                String lineBeginning = "";
                                for (int j = 0; j < beginningVerse.length() + 5 && j < currentLine.length(); j++) {
                                    lineBeginning = lineBeginning + currentLine.charAt(j);                              
                                
									//todo: make a function to strip markup from the line; use 
									//helloWorld = helloWorld.replace("\\v ",""); etc to remove tags 
									
									if (lineBeginning.equals("\\v " + beginningVerse)) {
								 
										//strip out markup
										/*for (int j = 0; j < currentLine.length(); j++) {
											if (tagFound && currentLine.charAt(j) != ' ') {
												tagText = tagText + currentLine.charAt(j);
											}
											else if (tagFound && currentLine.charAt(j) == ' ') {
												//check if tag is self-closing (like \v) 
												//or just styling (like \bk ... \bk*) 
												//or surrounds a block to delete (like \x ... \x*)
												
											}
											else if (currentLine.charAt(j) == '\\') {
												tagFound = true;
											}
											else {
												resultText += currentLine.charAt(j);
											}
										} */
										if (!currentLinePrinted) {
											System.out.println(currentLine);
											currentLinePrinted = true;
										}
										foundStartVerse = true;
										lineBeginning = "";
										if (beginningVerse == endingVerse) {
											foundEndVerse = true;
										}
									}
									else if (lineBeginning.equals("\\v " + endingVerse)) {
										foundEndVerse = true;
										if (!currentLinePrinted) {
											System.out.println(currentLine);
											currentLinePrinted = true;
										}
										currentLinePrinted = true;
									}
									else if (foundStartVerse && !foundEndVerse) {
										if (!currentLinePrinted) {
											System.out.println(currentLine);
											currentLinePrinted = true;
										}
									}
								}
                            }
							
                        }// end while scanner has next line
                    }
                    catch (Exception e) {
                        System.out.println("\nCould not find that file!");
                    }
                } //end if file exists
                else {
                    System.out.println("\nCould not find the file for this book of the Bible.");
                }
            } //end if validReference
            else
                System.out.println("Bad reference. " + invalidReason);
        } //end iteration over passed in parameters
    } //end main()
	
	public static String validateChapter(String invalidReason, String bookAbbreviation, String chapterNumber) { 
		boolean validReference = true; //delete this after fixing the switch
		switch (bookAbbreviation) {
                case "GEN": if (Integer.parseInt(chapterNumber) > 50) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "EXO": if (Integer.parseInt(chapterNumber) > 40) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "LEV": if (Integer.parseInt(chapterNumber) > 27) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "NUM": if (Integer.parseInt(chapterNumber) > 36) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "DEU": if (Integer.parseInt(chapterNumber) > 34) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "JOS": if (Integer.parseInt(chapterNumber) > 24) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "JDG": if (Integer.parseInt(chapterNumber) > 21) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "RUT": if (Integer.parseInt(chapterNumber) > 4) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "1SA": if (Integer.parseInt(chapterNumber) > 31) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "2SA": if (Integer.parseInt(chapterNumber) > 24) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "1KI": if (Integer.parseInt(chapterNumber) > 22) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
							//TODO: Finish fixing all these :(
                case "2KI": if (Integer.parseInt(chapterNumber) > 25) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "1CH": if (Integer.parseInt(chapterNumber) > 29) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "2CH": if (Integer.parseInt(chapterNumber) > 36) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "EZR": if (Integer.parseInt(chapterNumber) > 10) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "NEH": if (Integer.parseInt(chapterNumber) > 13) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "EST": if (Integer.parseInt(chapterNumber) > 10) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "JOB": if (Integer.parseInt(chapterNumber) > 42) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "PSA": if (Integer.parseInt(chapterNumber) > 150) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "PRO": if (Integer.parseInt(chapterNumber) > 31) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "ECC": if (Integer.parseInt(chapterNumber) > 12) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "SNG": if (Integer.parseInt(chapterNumber) > 8) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "ISA": if (Integer.parseInt(chapterNumber) > 66) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "JER": if (Integer.parseInt(chapterNumber) > 52) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "LAM": if (Integer.parseInt(chapterNumber) > 9) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "EZK": if (Integer.parseInt(chapterNumber) > 48) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "DAN": if (Integer.parseInt(chapterNumber) > 12) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "HOS": if (Integer.parseInt(chapterNumber) > 14) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "JOL": if (Integer.parseInt(chapterNumber) > 3) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "AMO": if (Integer.parseInt(chapterNumber) > 9) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "OBA": if (Integer.parseInt(chapterNumber) > 1) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "JON": if (Integer.parseInt(chapterNumber) > 4) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "MIC": if (Integer.parseInt(chapterNumber) > 7) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "NAM": if (Integer.parseInt(chapterNumber) > 3) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "HAB": if (Integer.parseInt(chapterNumber) > 3) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "ZEP": if (Integer.parseInt(chapterNumber) > 3) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "HAG": if (Integer.parseInt(chapterNumber) > 2) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "ZEC": if (Integer.parseInt(chapterNumber) > 14) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "MAL": if (Integer.parseInt(chapterNumber) > 4) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "MAT": if (Integer.parseInt(chapterNumber) > 28) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "MRK": if (Integer.parseInt(chapterNumber) > 16) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "LUK": if (Integer.parseInt(chapterNumber) > 24) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "JHN": if (Integer.parseInt(chapterNumber) > 21) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "ACT": if (Integer.parseInt(chapterNumber) > 28) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "ROM": if (Integer.parseInt(chapterNumber) > 16) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "1CO": if (Integer.parseInt(chapterNumber) > 16) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "2CO": if (Integer.parseInt(chapterNumber) > 13) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "GAL": if (Integer.parseInt(chapterNumber) > 6) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "EPH": if (Integer.parseInt(chapterNumber) > 6) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "PHP": if (Integer.parseInt(chapterNumber) > 4) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "COL": if (Integer.parseInt(chapterNumber) > 4) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "1TH": if (Integer.parseInt(chapterNumber) > 5) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "2TH": if (Integer.parseInt(chapterNumber) > 3) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "1TI": if (Integer.parseInt(chapterNumber) > 6) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "2TI": if (Integer.parseInt(chapterNumber) > 4) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "TIT": if (Integer.parseInt(chapterNumber) > 3) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "PHM": if (Integer.parseInt(chapterNumber) > 1) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "HEB": if (Integer.parseInt(chapterNumber) > 13) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "JAS": if (Integer.parseInt(chapterNumber) > 5) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "1PE": if (Integer.parseInt(chapterNumber) > 5) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "2PE": if (Integer.parseInt(chapterNumber) > 3) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "1JN": if (Integer.parseInt(chapterNumber) > 5) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "2JN": if (Integer.parseInt(chapterNumber) > 1) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "3JN": if (Integer.parseInt(chapterNumber) > 1) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "JUD": if (Integer.parseInt(chapterNumber) > 1) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                case "REV": if (Integer.parseInt(chapterNumber) > 22) {
								invalidReason += "That chapter does not exist in that book of the Bible. "; 
							}
							break;
                default: break;
            } //end chapter check switch
			
		return invalidReason;
	}
}