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
							&& bookName != "Song" 
							&& bookName != "Song of"
							&& bookName != "1" 
							&& bookName != "2" 
							&& bookName != "1st" 
							&& bookName != "2nd" 
							&& bookName != "First" 
							&& bookName != "Second")
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
			
			switch (bookName) {
case "Genesis":  bookAbbreviation = "GEN"; break;
case "GEN": bookAbbreviation = "GEN"; break;
case "Exodus":  bookAbbreviation = "EXO"; break;
case "EXO":  bookAbbreviation = "EXO"; break;
case "Leviticus":  bookAbbreviation = "LEV"; break;
case "LEV":  bookAbbreviation = "LEV"; break;
case "Numbers":  bookAbbreviation = "NUM"; break;
case "NUM":  bookAbbreviation = "NUM"; break;
case "Deuteronomy":  bookAbbreviation = "DEU"; break;
case "DEU":  bookAbbreviation = "DEU"; break;
case "Joshua":  bookAbbreviation = "JOS"; break;
case "JOS":  bookAbbreviation = "JOS"; break;
case "Judges":  bookAbbreviation = "JDG"; break;
case "JDG":  bookAbbreviation = "JDG"; break;
case "Ruth":  bookAbbreviation = "RUT"; break;
case "RUT":  bookAbbreviation = "RUT"; break;
case "1 Samuel":  bookAbbreviation = "1SA"; break;
case "1st Samuel":  bookAbbreviation = "1SA"; break;
case "First Samuel": bookAbbreviation = "1SA"; break;
case "1SA":  bookAbbreviation = "1SA"; break;
case "2 Samuel":  bookAbbreviation = "2SA"; break;
case "2nd Samuel":  bookAbbreviation = "2SA"; break;
case "Second Samuel":  bookAbbreviation = "2SA"; break;
case "2SA":  bookAbbreviation = "2SA"; break;
case "1 Kings":  bookAbbreviation = "1KI"; break;
case "1st Kings":  bookAbbreviation = "1KI"; break;
case "First Kings": bookAbbreviation = "1KI"; break;
case "1 King":  bookAbbreviation = "1KI"; break;
case "1st King":  bookAbbreviation = "1KI"; break;
case "First King": bookAbbreviation = "1KI"; break;
case "1KI": bookAbbreviation = "1KI"; break;
case "2 Kings":  bookAbbreviation = "2KI"; break;
case "2nd Kings":  bookAbbreviation = "2KI"; break;
case "Second Kings":  bookAbbreviation = "2KI"; break;
case "2 King":  bookAbbreviation = "2KI"; break;
case "2nd King":  bookAbbreviation = "2KI"; break;
case "Second King":  bookAbbreviation = "2KI"; break;
case "2KI":  bookAbbreviation = "2KI"; break;
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
						 
				default: bookAbbreviation = "Not found";
						 break;
			}
			if (validReference)
				System.out.println("Book name: " + bookName + ", Book abbreviation: " + bookAbbreviation + ", Chapter: " + chapterNumber + ", Beginning verse: " + beginningVerse + ", Ending verse: " + endingVerse);
			else
				System.out.println("Bad reference");
		}
    }
}