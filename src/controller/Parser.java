package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final HashMap<String, Integer> id = new HashMap<>();
    private final HashMap<String, Integer> op = new HashMap<>();
    private final HashMap<String, Integer> delim = new HashMap<>();
    private final HashMap<String, Integer> decl = new HashMap<>();
    private final HashMap<String, Integer> flow = new HashMap<>();
    private final HashMap<String, Integer> type = new HashMap<>();
    private final HashMap<String, Integer> comment = new HashMap<>();

    public void lexicalAnalyzer(String path) throws IOException  {
        FileInputStream stream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffRead = new BufferedReader(reader);
        String line = buffRead.readLine();

        String idRegex = "[a-zA-Z][a-zA-Z0-9]*";
        String opRegex = "[+\\-*/<!.,=&&]";
        String delimRegex = "[(){}\\[\\];]";
        String declRegex = "class|extends|public|static|void|main|length|this|new";
        String fluxoRegex = "if|else|while|return|System\\.out\\.println";
        String tipoRegex = "boolean|int |true|false|String";
        String comentRegex = "\\/\\*([\\s\\S]*?)\\*\\/";

        Pattern opPattern = Pattern.compile(opRegex);
        Pattern delimPattern = Pattern.compile(delimRegex);
        Pattern declPattern = Pattern.compile(declRegex);
        Pattern fluxoPattern = Pattern.compile(fluxoRegex);
        Pattern tipoPattern = Pattern.compile(tipoRegex);
        Pattern idPattern = Pattern.compile(idRegex);
        Pattern commentPattern = Pattern.compile(comentRegex);

        Matcher matcher;
        String lineOfCode;

        while (line != null) {
            lineOfCode = line.replaceAll(comentRegex, "/* */");

            matcher = commentPattern.matcher(lineOfCode);
            while (matcher.find()) {
                addToken(this.comment, "COMENT," + matcher.group());
            }

            lineOfCode = lineOfCode.replaceAll(comentRegex, " ");

            matcher = opPattern.matcher(lineOfCode);
            while (matcher.find()) {
                addToken(this.op, "OP," + matcher.group());
            }

            matcher = delimPattern.matcher(lineOfCode);
            while (matcher.find()) {
                addToken(this.delim, "DELIM," + matcher.group());
            }

            matcher = declPattern.matcher(lineOfCode);
            while (matcher.find()) {
                addToken(this.decl, "DECL," + matcher.group());
            }

            matcher = fluxoPattern.matcher(lineOfCode);
            while (matcher.find()) {
                addToken(this.flow, "FLUXO," + matcher.group());
            }

            matcher = tipoPattern.matcher(lineOfCode);
            while (matcher.find()) {
                addToken(this.type, "TIPO," + matcher.group());
            }

            lineOfCode = lineOfCode.replaceAll(declRegex, " ");
            lineOfCode = lineOfCode.replaceAll(delimRegex, " ");
            lineOfCode = lineOfCode.replaceAll(fluxoRegex, " ");
            lineOfCode = lineOfCode.replaceAll(opRegex, " ");
            lineOfCode = lineOfCode.replaceAll(tipoRegex, " ");

            matcher = idPattern.matcher(lineOfCode);
            while (matcher.find()) {
                addToken(this.id, "ID," + matcher.group());
            }

            line = buffRead.readLine();
        }
    }

    private void addToken(HashMap<String, Integer> hashMap, String token) {
        if (hashMap.containsKey(token)) {
            hashMap.put(token, hashMap.get(token) + 1);
        } else {
            hashMap.put(token, 1);
        }
    }

    public void printTokens() {
        for (String token : this.id.keySet()) {
            System.out.println("<" + token + "> numero de ocorrencias " + this.id.get(token));
        }

        System.out.println();

        for (String token : this.op.keySet()) {
            System.out.println("<" + token + "> numero de ocorrencias " + this.op.get(token));
        }

        System.out.println();

        for (String token : this.delim.keySet()) {
            System.out.println("<" + token + "> numero de ocorrencias " + this.delim.get(token));
        }

        System.out.println();

        for (String token : this.decl.keySet()) {
            System.out.println("<" + token + "> numero de ocorrencias " + this.decl.get(token));
        }

        System.out.println();

        for (String token : this.flow.keySet()) {
            System.out.println("<" + token + "> numero de ocorrencias " + this.flow.get(token));
        }

        System.out.println();

        for (String token : this.type.keySet()) {
            System.out.println("<" + token + "> numero de ocorrencias " + this.type.get(token));
        }

        System.out.println();

        for (String token : this.comment.keySet()) {
            System.out.println("<" + token + "> numero de ocorrencias " + this.comment.get(token));
        }
    }
}

