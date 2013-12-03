package fmat.arquitectura.Seguridad.ContrasenaSegura;

public class PasswordSecurity {

    public int passwordScore(String pwd) {

        String sAlphas = "abcdefghijklmnopqrstuvwxyz";
        String sNumerics = "01234567890";

        int nMinPwdLen = 8;

        if (!pwd.isEmpty()) {
            nScore = pwd.length() * nMultLength;
            nLength = pwd.length();
            String arrPwd[] = pwd.replaceAll("\\s", "").split("\\s*");
            int arrPwdLen = arrPwd.length;

            /*
             * Loop through password to check for Symbol, Numeric, Lowercase and
             * Uppercase pattern matches
             */
            for (int a = 0; a < arrPwdLen; a++) {
                if (arrPwd[a].matches("[A-Z]")) {
                    if (nTmpAlphaUC != 0) {
                        if ((nTmpAlphaUC + 1) == a) {
                            nConsecAlphaUC++;
                            nConsecCharType++;
                        }
                    }
                    nTmpAlphaUC = a;
                    nAlphaUC++;
                } else if (arrPwd[a].matches("[a-z]")) {
                    if (nTmpAlphaLC != 0) {
                        if ((nTmpAlphaLC + 1) == a) {
                            nConsecAlphaLC++;
                            nConsecCharType++;
                        }
                    }
                    nTmpAlphaLC = a;
                    nAlphaLC++;
                } else if (arrPwd[a].matches("[0-9]")) {
                    if (a > 0 && a < (arrPwdLen - 1)) {
                        nMidChar++;
                    }
                    if (nTmpNumber != 0) {
                        if ((nTmpNumber + 1) == a) {
                            nConsecNumber++;
                            nConsecCharType++;
                        }
                    }
                    nTmpNumber = a;
                    nNumber++;
                } else if (arrPwd[a].matches("[^a-zA-Z0-9_]")) {
                    if (a > 0 && a < (arrPwdLen - 1)) {
                        nMidChar++;
                    }
                    if (nTmpSymbol != 0) {
                        if ((nTmpSymbol + 1) == a) {
                            nConsecSymbol++;
                            nConsecCharType++;
                        }
                    }
                    nTmpSymbol = a;
                    nSymbol++;
                }
                /*
                 * Internal loop through password to check for repeated
                 * characters
                 */
                for (int b = 0; b < arrPwdLen; b++) {
                    if (arrPwd[a].toLowerCase().compareTo(arrPwd[b].toLowerCase()) == 0 && a != b) {
                        nRepChar++;
                    }
                }
            }

            /*
             * Check for sequential alpha string patterns (forward and reverse)
             */
            for (int s = 0; s < 23; s++) {
                String sFwd = sAlphas.substring(s, s + 3);
                String sRev = new StringBuilder(sFwd).reverse().toString();
                if (pwd.toLowerCase().indexOf(sFwd) != -1 || pwd.toLowerCase().indexOf(sRev) != -1) {
                    nSeqAlpha++;
                    nSeqChar++;
                }
            }

            /*
             * Check for sequential numeric string patterns (forward and
             * reverse)
             */
            for (int s = 0; s < 8; s++) {
                String sFwd = sNumerics.substring(s, s + 3);
                String sRev = new StringBuilder(sFwd).reverse().toString();
                if (pwd.toLowerCase().indexOf(sFwd) != -1 || pwd.toLowerCase().indexOf(sRev) != -1) {
                    nSeqNumber++;
                    nSeqChar++;
                }
            }
            
            /*
             * Modify overall score value based on usage vs requirements
             */
            if (nAlphaUC > 0 && nAlphaUC < nLength) {
                nScore = nScore + ((nLength - nAlphaUC) * 2);
            }
            if (nAlphaLC > 0 && nAlphaLC < nLength) {
                nScore = (nScore + ((nLength - nAlphaLC) * 2));
            }
            if (nNumber > 0 && nNumber < nLength) {
                nScore = (nScore + (nNumber * nMultNumber));
            }
            if (nSymbol > 0) {
                nScore = (nScore + (nSymbol * nMultSymbol));
            }
            if (nMidChar > 0) {
                nScore = (nScore + (nMidChar * nMultMidChar));
            }


            /*
             * Point deductions for poor practices
             */
            if ((nAlphaLC > 0 || nAlphaUC > 0) && nSymbol == 0 && nNumber == 0) {  // Only Letters
                nScore = (nScore - nLength);
            }
            if (nAlphaLC == 0 && nAlphaUC == 0 && nSymbol == 0 && nNumber > 0) {  // Only Numbers
                nScore = (nScore - nLength);
            }
            if (nRepChar > 0) {  // Same character exists more than once
                nScore = (nScore - (nRepChar * nRepChar));
            }
            if (nConsecAlphaUC > 0) {  // Consecutive Uppercase Letters exist
                nScore = (nScore - (nConsecAlphaUC * nMultConsecAlphaUC));
            }
            if (nConsecAlphaLC > 0) {  // Consecutive Lowercase Letters exist
                nScore = (nScore - (nConsecAlphaLC * nMultConsecAlphaLC));
            }
            if (nConsecNumber > 0) {  // Consecutive Numbers exist
                nScore = (nScore - (nConsecNumber * nMultConsecNumber));
            }
            if (nSeqAlpha > 0) {  // Sequential alpha strings exist (3 characters or more)
                nScore = (nScore - (nSeqAlpha * nMultSeqAlpha));
            }
            if (nSeqNumber > 0) {  // Sequential numeric strings exist (3 characters or more)
                nScore = (nScore - (nSeqNumber * nMultSeqNumber));
            }

            /*
             * Determine if mandatory requirements have been met
             */
            int nMinReqChars;
            {
                int arrChars[] = {nLength, nAlphaUC, nAlphaLC, nNumber, nSymbol};
                int arrCharsLen = arrChars.length;
                for (int c = 0; c < arrCharsLen; c++) {
                    int minVal;
                    if (c == 0) {
                        minVal = (nMinPwdLen - 1);
                    } else {
                        minVal = 0;
                    }
                    if (arrChars[c] == (minVal + 1)) {
                        nReqChar++;
                    } else if (arrChars[c] > (minVal + 1)) {
                        nReqChar++;
                    }
                }
                nRequirements = nReqChar;

                if (pwd.length() >= nMinPwdLen) {
                    nMinReqChars = 3;
                } else {
                    nMinReqChars = 4;
                }
                if (nRequirements > nMinReqChars) {  // One or more required characters exist
                    nScore = (nScore + (nRequirements * 2));
                }
            }
        }

        if (nScore > 100) {
            nScore = 100;
        } else if (nScore < 0) {
            nScore = 0;
        }
        return nScore;
    }

    public String passwordComplexity(int nScore) {
        String sComplexity=null;

        if (nScore > 100) {
            nScore = 100;
        } else if (nScore < 0) {
            nScore = 0;
        }

        if (nScore >= 0 && nScore < 20) {
            sComplexity = "Muy débil";
        } else if (nScore >= 20 && nScore < 40) {
            sComplexity = "Débil";
        } else if (nScore >= 40 && nScore < 60) {
            sComplexity = "Buena";
        } else if (nScore >= 60 && nScore < 80) {
            sComplexity = "Fuerte";
        } else if (nScore >= 80 && nScore <= 100) {
            sComplexity = "Muy Fuerte";
        }

        return sComplexity;
    }
    
    int nScore = 0;

    int nLength;
    int nAlphaUC = 0;
    int nAlphaLC = 0;
    int nNumber = 0;
    int nSymbol = 0;
    int nMidChar = 0;
    int nRequirements;
    int nRepChar = 0;
    int nConsecAlphaUC = 0;
    int nConsecAlphaLC = 0;
    int nConsecNumber = 0;
    int nConsecSymbol = 0;
    int nConsecCharType = 0;
    int nSeqAlpha = 0;
    int nSeqNumber = 0;
    int nSeqChar = 0;
    int nReqChar = 0;
    int nMultLength = 4;
    int nMultNumber = 4;
    int nMultSymbol = 6;
    int nMultMidChar = 2;
    int nMultConsecAlphaUC = 2;
    int nMultConsecAlphaLC = 2;
    int nMultConsecNumber = 2;
    int nMultSeqAlpha = 3;
    int nMultSeqNumber = 3;
    int nTmpAlphaUC = 0;
    int nTmpAlphaLC = 0;
    int nTmpNumber = 0;
    int nTmpSymbol = 0;
}
