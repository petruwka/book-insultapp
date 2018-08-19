package org.openshift;

import java.sql.*;
import java.util.Random;

public class InsultGenerator {
    public String generateInsult() {
        String words[][] = {{"Artless", "Bawdy", "Beslubbering"}, {"Base-court", "Bat-fowling", "Beef-witted"}, {"Apple-john", "Baggage", "Barnacle"}};
        String vowels = "AEIOU";
        String article = "an";
        String firstAdjective = words[0][new Random().nextInt(words[0].length)];
        String secondAdjective = words[1][new Random().nextInt(words[1].length)];
        String noun = words[2][new Random().nextInt(words[2].length)];
        if (vowels.indexOf(firstAdjective.charAt(0)) == -1) {
            article = "a";
        }
        return String.format("Thou art %s %s %s %s!", article, firstAdjective, secondAdjective, noun);
    }

    public String generateInsultFromDb() {
        String vowels = "AEIOU";
        String article = "an";
        String theInsult = "";


        String sql = "select a.string as first, b.string as second, c.string as noun" +
                " from short_adjective a, long_adjective b, noun c order by random() limit 1";

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String first = rs.getString("first");
                String second = rs.getString("second");
                String noun = rs.getString("noun");

                if (vowels.indexOf(first.charAt(0)) == -1) {
                    article = "a";
                }
                theInsult = String.format("Thou art %s %s %s %s", article, first, second, noun);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return theInsult;
    }


}


