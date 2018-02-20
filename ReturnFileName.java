public class ReturnFileName {

    public static void main(String[] args) {
        for(int i=0; i < args.length; i++) {
			
			String reference = "";
			String bookName = "";
			String bookAbbreviation = "";
			String chapterNumber = "";
			String beginningVerse = "";
			String endingVerse = "";
			boolean finishedBookName = false;
			boolean finishedChapter = false;
			boolean foundDash = false;
			boolean validReference = true;
			
			reference = args[i];
			
			//parse the reference
			for (int j = 0; j < reference.length(); j++) {
				//get the book name
				if (!finishedBookName) {
					if (reference.charAt(j) == ' ' 
							&& !bookName.equals("Song") 
							&& !bookName.equals("song") 
							&& !bookName.equals("Song of")
							&& !bookName.equals("song of")
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
			}
			
			if (endingVerse.length() == 0)
				endingVerse = beginningVerse;
			
			validReference = !(bookName == null || chapterNumber == null || beginningVerse == null);
			
			//map the book name to the abbreviation
			switch (bookName) {
				case "Genesis":  bookAbbreviation = "GEN"; break;
				case "GEN": bookAbbreviation = "GEN"; break;
				case "genesis": bookAbbreviation = "GEN"; break;
				case "GENESIS": bookAbbreviation = "GEN"; break;
				case "gen": bookAbbreviation = "GEN"; break;
				case "Gene": bookAbbreviation = "GEN"; break;
				case "gene": bookAbbreviation = "GEN"; break;
				case "GENE": bookAbbreviation = "GEN"; break;
				case "Genes": bookAbbreviation = "GEN"; break;
				case "genes": bookAbbreviation = "GEN"; break;
				case "GENES": bookAbbreviation = "GEN"; break;
				case "Exodus":  bookAbbreviation = "EXO"; break;
				case "EXO":  bookAbbreviation = "EXO"; break;
				case "exodus":  bookAbbreviation = "EXO"; break; 
				case "EXODUS":  bookAbbreviation = "EXO"; break;
				case "Exod":  bookAbbreviation = "EXO"; break;
				case "exod":  bookAbbreviation = "EXO"; break;
				case "EXOD":  bookAbbreviation = "EXO"; break;
				case "Leviticus":  bookAbbreviation = "LEV"; break;
				case "LEV":  bookAbbreviation = "LEV"; break;
				case "leviticus":  bookAbbreviation = "LEV"; break;
				case "LEVITICUS":  bookAbbreviation = "LEV"; break;
				case "Levi":  bookAbbreviation = "LEV"; break;
				case "levi":  bookAbbreviation = "LEV"; break;
				case "LEVI":  bookAbbreviation = "LEV"; break;
				case "Levit":  bookAbbreviation = "LEV"; break;
				case "levit":  bookAbbreviation = "LEV"; break;
				case "LEVIT":  bookAbbreviation = "LEV"; break;
				case "lev":  bookAbbreviation = "LEV"; break;
				case "Leveticus":  bookAbbreviation = "LEV"; break;
				case "Numbers":  bookAbbreviation = "NUM"; break;
				case "NUM":  bookAbbreviation = "NUM"; break;
				case "numbers":  bookAbbreviation = "NUM"; break;
				case "NUMBERS":  bookAbbreviation = "NUM"; break;
				case "num":  bookAbbreviation = "NUM"; break;
				case "Num":  bookAbbreviation = "NUM"; break;
				case "Numb":  bookAbbreviation = "NUM"; break;
				case "numb":  bookAbbreviation = "NUM"; break;
				case "NUMB":  bookAbbreviation = "NUM"; break;
				case "Number":  bookAbbreviation = "NUM"; break;
				case "Deuteronomy":  bookAbbreviation = "DEU"; break;
				case "DEU":  bookAbbreviation = "DEU"; break;
				case "Dueteronomy":  bookAbbreviation = "DEU"; break;
				case "deuteronomy":  bookAbbreviation = "DEU"; break;
				case "DEUTERONOMY":  bookAbbreviation = "DEU"; break;
				case "dueteronomy":  bookAbbreviation = "DEU"; break;
				case "DUETERONOMY":  bookAbbreviation = "DEU"; break;
				case "Deut":  bookAbbreviation = "DEU"; break;
				case "deut":  bookAbbreviation = "DEU"; break;
				case "DEUT":  bookAbbreviation = "DEU"; break;
				case "Duet":  bookAbbreviation = "DEU"; break;
				case "duet":  bookAbbreviation = "DEU"; break;
				case "DUET":  bookAbbreviation = "DEU"; break;
				case "Deuter":  bookAbbreviation = "DEU"; break;
				case "Dueter":  bookAbbreviation = "DEU"; break;
				case "deuter":  bookAbbreviation = "DEU"; break;
				case "dueter":  bookAbbreviation = "DEU"; break;
				case "DEUTER":  bookAbbreviation = "DEU"; break;
				case "DUETER":  bookAbbreviation = "DEU"; break;
				case "Joshua":  bookAbbreviation = "JOS"; break;
				case "JOS":  bookAbbreviation = "JOS"; break;
				case "Josh":  bookAbbreviation = "JOS"; break;
				case "JOSH":  bookAbbreviation = "JOS"; break;
				case "josh":  bookAbbreviation = "JOS"; break;
				case "joshua":  bookAbbreviation = "JOS"; break;
				case "jos":  bookAbbreviation = "JOS"; break;
				case "Jos":  bookAbbreviation = "JOS"; break;
				case "JOSHUA":  bookAbbreviation = "JOS"; break;
				case "Judges":  bookAbbreviation = "JDG"; break;
				case "JDG":  bookAbbreviation = "JDG"; break;
				case "jdg":  bookAbbreviation = "JDG"; break;
				case "judges":  bookAbbreviation = "JDG"; break;
				case "JUDGES":  bookAbbreviation = "JDG"; break;
				case "Judg":  bookAbbreviation = "JDG"; break;
				case "judg":  bookAbbreviation = "JDG"; break;
				case "JUDG":  bookAbbreviation = "JDG"; break;
				case "Jdg":  bookAbbreviation = "JDG"; break;
				case "Judge":  bookAbbreviation = "JDG"; break;
				case "judge":  bookAbbreviation = "JDG"; break;
				case "JUDGE":  bookAbbreviation = "JDG"; break;
				case "JGS":  bookAbbreviation = "JDG"; break;
				case "Jgs":  bookAbbreviation = "JDG"; break;
				case "jgs":  bookAbbreviation = "JDG"; break;
				case "Ruth":  bookAbbreviation = "RUT"; break;
				case "RUT":  bookAbbreviation = "RUT"; break;
				case "ruth":  bookAbbreviation = "RUT"; break;
				case "RUTH":  bookAbbreviation = "RUT"; break;
				case "Ru":  bookAbbreviation = "RUT"; break;
				case "ru":  bookAbbreviation = "RUT"; break;
				case "RU":  bookAbbreviation = "RUT"; break;
				case "1 Samuel":  bookAbbreviation = "1SA"; break;
				case "1 samuel": bookAbbreviation = "1SA"; break;
				case "1 sam": bookAbbreviation = "1SA"; break;
				case "1st Samuel":  bookAbbreviation = "1SA"; break;
				case "First Samuel": bookAbbreviation = "1SA"; break;
				case "1 SAMUEL": bookAbbreviation = "1SA"; break;
				case "1SA":  bookAbbreviation = "1SA"; break;
				case "1 Sam":  bookAbbreviation = "1SA"; break;
				case "First Sam":  bookAbbreviation = "1SA"; break;
				case "1st Sam":  bookAbbreviation = "1SA"; break;
				case "1sa":  bookAbbreviation = "1SA"; break;
				case "1Sa":  bookAbbreviation = "1SA"; break;
				case "2 Samuel":  bookAbbreviation = "2SA"; break;
				case "2nd Samuel":  bookAbbreviation = "2SA"; break;
				case "Second Samuel":  bookAbbreviation = "2SA"; break;
				case "2 sam":  bookAbbreviation = "2SA"; break;
				case "2 samuel":  bookAbbreviation = "2SA"; break;
				case "2 SAMUEL": bookAbbreviation = "2SA"; break;
				case "2nd sam":  bookAbbreviation = "2SA"; break;
				case "2SA":  bookAbbreviation = "2SA"; break;
				case "2 Sam":  bookAbbreviation = "2SA"; break;
				case "2nd Sam":  bookAbbreviation = "2SA"; break;
				case "Second Sam":  bookAbbreviation = "2SA"; break;
				case "2sa":  bookAbbreviation = "2SA"; break;
				case "2Sa":  bookAbbreviation = "2SA"; break;
				case "1 Kings":  bookAbbreviation = "1KI"; break;
				case "1st Kings":  bookAbbreviation = "1KI"; break;
				case "First Kings": bookAbbreviation = "1KI"; break;
				case "1 King":  bookAbbreviation = "1KI"; break;
				case "1st King":  bookAbbreviation = "1KI"; break;
				case "First King": bookAbbreviation = "1KI"; break;
				case "1KI": bookAbbreviation = "1KI"; break;
				case "1 king": bookAbbreviation = "1KI"; break;
				case "1 kings": bookAbbreviation = "1KI"; break;
				case "1 ki": bookAbbreviation = "1KI"; break;
				case "1 Ki": bookAbbreviation = "1KI"; break;
				case "1Ki": bookAbbreviation = "1KI"; break;
				case "1ki": bookAbbreviation = "1KI"; break;
				case "1 KINGS": bookAbbreviation = "1KI"; break;
				case "2 Kings":  bookAbbreviation = "2KI"; break;
				case "2nd Kings":  bookAbbreviation = "2KI"; break;
				case "Second Kings":  bookAbbreviation = "2KI"; break;
				case "2 King":  bookAbbreviation = "2KI"; break;
				case "2nd King":  bookAbbreviation = "2KI"; break;
				case "Second King":  bookAbbreviation = "2KI"; break;
				case "2KI":  bookAbbreviation = "2KI"; break;
				case "2 king":  bookAbbreviation = "2KI"; break;
				case "2 kings":  bookAbbreviation = "2KI"; break;
				case "2 ki":  bookAbbreviation = "2KI"; break;
				case "2 Ki":  bookAbbreviation = "2KI"; break;
				case "2Ki":  bookAbbreviation = "2KI"; break;
				case "2ki":  bookAbbreviation = "2KI"; break;
				case "2 KINGS":  bookAbbreviation = "2KI"; break;

case "1 Chronicles":  bookAbbreviation = "1CH"; break;		
case "1CH":  bookAbbreviation = "1CH"; break;				
case "2 Chronicles":  bookAbbreviation = "2CH"; break;
case "2CH":  bookAbbreviation = "2CH"; break;
case "Ezra":  bookAbbreviation = "EZR"; break;
case "EZR":  bookAbbreviation = "EZR"; break;
case "Nehemiah":  bookAbbreviation = "NEH"; break;
case "NEH":  bookAbbreviation = "NEH"; break;
case "Esther":  bookAbbreviation = "EST"; break;
case "EST":  bookAbbreviation = "EST"; break;
case "Job":  bookAbbreviation = "JOB"; break;
case "JOB":  bookAbbreviation = "JOB"; break;
case "Psalms":  bookAbbreviation = "PSA"; break;
case "PSA":  bookAbbreviation = "PSA"; break;
case "Proverbs":  bookAbbreviation = "PRO"; break;
case "PRO":  bookAbbreviation = "PRO"; break;
case "Ecclesiastes":  bookAbbreviation = "ECC"; break;
case "ECC":  bookAbbreviation = "ECC"; break;
case "Song of Solomon":  bookAbbreviation = "SNG"; break;
case "Song of Songs":  bookAbbreviation = "SNG"; break;
case "SNG":  bookAbbreviation = "SNG"; break;

				case "Isaiah": bookAbbreviation = "ISA"; break;
				case "ISA": bookAbbreviation = "ISA"; break;
				case "isa": bookAbbreviation = "ISA"; break; 
				case "isaiah": bookAbbreviation = "ISA"; break;
				case "ISAIAH": bookAbbreviation = "ISA"; break;
				case "Jeremiah": bookAbbreviation = "JER"; break;
				case "JER": bookAbbreviation = "JER"; break;
				case "jeremiah": bookAbbreviation = "JER"; break;
				case "jer": bookAbbreviation = "JER"; break;
				case "JEREMIAH": bookAbbreviation = "JER"; break;
				case "Lamentations": bookAbbreviation = "LAM"; break;
				case "LAM": bookAbbreviation = "LAM"; break;
				case "lam": bookAbbreviation = "LAM"; break;
				case "lamen": bookAbbreviation = "LAM"; break;
				case "Lamen": bookAbbreviation = "LAM"; break;
				case "LAMENTATIONS": bookAbbreviation = "LAM"; break;
				case "Ezekiel": bookAbbreviation = "EZK"; break;
				case "Exekiel": bookAbbreviation = "EZK"; break;
				case "EZK": bookAbbreviation = "EZK"; break;
				case "Ezek": bookAbbreviation = "EZK"; break;
				case "ezek": bookAbbreviation = "EZK"; break;
				case "eze": bookAbbreviation = "EZK"; break;
				case "ezk": bookAbbreviation = "EZK"; break;
				case "Eze": bookAbbreviation = "EZK"; break;
				case "ezekiel": bookAbbreviation = "EZK"; break;
				case "Ezekeil": bookAbbreviation = "EZK"; break;
				case "ezekeil": bookAbbreviation = "EZK"; break;
				case "EZEKIEL": bookAbbreviation = "EZK"; break;
				case "EZEKEIL": bookAbbreviation = "EZK"; break;
				case "Daniel": bookAbbreviation = "DAN"; break;
				case "daniel": bookAbbreviation = "DAN"; break;
				case "DANIEL": bookAbbreviation = "DAN"; break;
				case "dan": bookAbbreviation = "DAN"; break;
				case "DAN": bookAbbreviation = "DAN"; break;
				case "Dan": bookAbbreviation = "DAN"; break;
				case "Daneil": bookAbbreviation = "DAN"; break;
				case "Dnl": bookAbbreviation = "DAN"; break;
				case "DNL": bookAbbreviation = "DAN"; break;
				case "Hosea": bookAbbreviation = "HOS"; break;
				case "hosea": bookAbbreviation = "HOS"; break;
				case "HOSEA": bookAbbreviation = "HOS"; break;
				case "HOS": bookAbbreviation = "HOS"; break;
				case "Hos": bookAbbreviation = "HOS"; break;
				case "hos": bookAbbreviation = "HOS"; break;
				case "Joel": bookAbbreviation = "JOL"; break;
				case "joel": bookAbbreviation = "JOL"; break;
				case "JOEL": bookAbbreviation = "JOL"; break;
				case "jol": bookAbbreviation = "JOL"; break;
				case "JOL": bookAbbreviation = "JOL"; break;
				case "Joe": bookAbbreviation = "JOL"; break;
				case "Amos": bookAbbreviation = "AMO"; break;
				case "AMO": bookAbbreviation = "AMO"; break;
				case "AMOS": bookAbbreviation = "AMO"; break;
				case "amos": bookAbbreviation = "AMO"; break;
				case "amo": bookAbbreviation = "AMO"; break;
				case "Ams": bookAbbreviation = "AMO"; break;
				case "ams": bookAbbreviation = "AMO"; break;
				case "AMS": bookAbbreviation = "AMO"; break;
				case "Obadiah": bookAbbreviation = "OBA"; break;
				case "OBA": bookAbbreviation = "OBA"; break;
				case "oba": bookAbbreviation = "OBA"; break;
				case "Obad": bookAbbreviation = "OBA"; break;
				case "OBAD": bookAbbreviation = "OBA"; break;
				case "obad": bookAbbreviation = "OBA"; break;
				case "OBADIAH": bookAbbreviation = "OBA"; break;
				case "obadiah": bookAbbreviation = "OBA"; break;
				case "Jonah": bookAbbreviation = "JON"; break;
				case "jonah": bookAbbreviation = "JON"; break;
				case "JON": bookAbbreviation = "JON"; break;
				case "jon": bookAbbreviation = "JON"; break;
				case "JONAH": bookAbbreviation = "JON"; break;
				case "Micah": bookAbbreviation = "MIC"; break;
				case "MIC": bookAbbreviation = "MIC"; break;
				case "mic": bookAbbreviation = "MIC"; break;
				case "MICAH": bookAbbreviation = "MIC"; break;
				case "micah": bookAbbreviation = "MIC"; break;
				case "Mica": bookAbbreviation = "MIC"; break;
				case "mica": bookAbbreviation = "MIC"; break;
				case "MICA": bookAbbreviation = "MIC"; break;

						 
				default: validReference = false;
						 break;
			}
			if (validReference)
				//print the parsed reference for troubleshooting
				System.out.println("Book name: " + bookName + ", Book abbreviation: " + bookAbbreviation + ", Chapter: " + chapterNumber + ", Beginning verse: " + beginningVerse + ", Ending verse: " + endingVerse);
			else
				System.out.println("Bad reference");
		}
    }
}