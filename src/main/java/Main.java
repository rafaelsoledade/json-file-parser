import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();
        try {

            // File writer
            Writer writer = new FileWriter("/Users/rafaelmatos/Desktop/new_input.json");

            // Output data json parsed object
            Object outputObj = parser.parse(new FileReader("/Users/rafaelmatos/Desktop/out.json"));
            JSONObject jsonObjectOutputData = (JSONObject) outputObj;

            // Input data json parsed object
            Object inputObj = parser.parse(new FileReader("/Users/rafaelmatos/Desktop/input.json"));
            JSONObject jsonObjectInputData = (JSONObject) inputObj;

            // Error data json parsed object
            Object errorObj = parser.parse(new FileReader("/Users/rafaelmatos/Desktop/error.json"));
            JSONObject jsonObjectErrorData = (JSONObject) errorObj;

            // Error data json parsed object
            Object newInputObj = parser.parse(new FileReader("/Users/rafaelmatos/Desktop/new_input_analysis.json"));
            JSONObject jsonObjectNewInputData = (JSONObject) newInputObj;

            newListOfInputAnalysis(jsonObjectNewInputData);

            //List<String> processedTransactionsExternalIds = listOfProcessedTransactionsByExternalId(jsonObjectOutputData);
            //listOfUnprocessedTransactions(jsonObjectInputData, processedTransactionsExternalIds, writer);

            //listOfErrorTransactions(jsonObjectErrorData);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get a list of all externalId of successfully processed transactions
     *
     * @param jsonObjectOutputData
     * @return
     */
    public static List<String> listOfProcessedTransactionsByExternalId(JSONObject jsonObjectOutputData) {

        List<String> processedTransactionsListById = new ArrayList<String>();

        JSONArray outputTransactionsStatusList = (JSONArray) jsonObjectOutputData.get("transactionsStatus");
        Iterator<JSONObject> iterator = outputTransactionsStatusList.iterator();

        int i = 0;
        JSONObject outputFinancialTransaction;

        while (iterator.hasNext()) {

            outputFinancialTransaction = (JSONObject) iterator.next().get("financialTransaction");

            String processedExternalId = (String) outputFinancialTransaction.get("externalId");
            processedTransactionsListById.add(processedExternalId);

            i++;
        }
        System.out.println("Total processed transactions (in out.json) -> " + i);
        return processedTransactionsListById;
    }

    /**
     * Populate new_input.json file with unprocessed transactions.
     * This means that new_input.json will have all the transactions in input.json that are not already in out.json
     *
     * @param jsonObjectInputData
     * @return
     */
    public static void listOfUnprocessedTransactions(JSONObject jsonObjectInputData, List<String> processedTransactionsListById, Writer writer) {

        JSONArray unprocessedTransactions = new JSONArray();

        JSONArray inputTransactionsStatusList = (JSONArray) jsonObjectInputData.get("transactions");
        Iterator<JSONObject> iterator = inputTransactionsStatusList.iterator();

        int i = 0;
        int totalTransactions = 0;

        while (iterator.hasNext()) {

            JSONObject curr = (JSONObject) iterator.next();
            String inputExternalId = (String) curr.get("externalId");

            if(!processedTransactionsListById.contains(inputExternalId)) {

                unprocessedTransactions.add(curr);
                //unprocessedTransactions.add(inputTransactionsStatusList.get(i));
                i++;
            }
            totalTransactions++;
        }
        System.out.println("Total input transactions (in input.json) -> " + totalTransactions);
        System.out.println("Total input transactions (in input.json) that weren't processed -> " + i);

        try {
            unprocessedTransactions.writeJSONString(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Populate new_input.json file with unprocessed transactions.
     * This means that new_input.json will have all the transactions in input.json that are not already in out.json
     *
     * @param jsonObjectInputData
     * @return
     */
    public static List<String> listOfProcessedTransactionsByExternalId(JSONObject jsonObjectInputData, List<String> processedTransactionsListById) {

        JSONArray outputTransactionsStatusList = (JSONArray) jsonObjectInputData.get("transactions");
        Iterator<JSONObject> iterator = outputTransactionsStatusList.iterator();

        int i = 0;
        int totalTransactions = 0;
        JSONObject inputFinancialTransaction;

        while (iterator.hasNext()) {

            //inputFinancialTransaction = (JSONObject) iterator.next().get("financialTransaction");

            String inputExternalId = (String) iterator.next().get("externalId");

            if(!processedTransactionsListById.contains(inputExternalId)) {

                //acrescentar entrada completa para processamento

                i++;
            }
            totalTransactions++;
        }
        System.out.println("Total input transactions (in input.json) -> " + totalTransactions);
        System.out.println("Total input transactions (in input.json) that weren't processed -> " + i);
        return processedTransactionsListById;
    }


    public static void listOfErrorTransactions(JSONObject jsonObjectErrorData) {

        JSONArray errorTransactionsStatusList = (JSONArray) jsonObjectErrorData.get("transactions");
        Iterator<JSONObject> iterator = errorTransactionsStatusList.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            i++;
        }
        System.out.println("Total original error transactions (error.json count) -> " + i);
    }




    public static void newListOfInputAnalysis(JSONObject jsonObjectNewInputData) {

        JSONArray newInputTransactionsStatusList = (JSONArray) jsonObjectNewInputData.get("transactions");
        Iterator<JSONObject> iterator = newInputTransactionsStatusList.iterator();

        int totalTransactions = 0;

        while (iterator.hasNext()) {

            JSONObject curr = (JSONObject) iterator.next();

            totalTransactions++;

            if(totalTransactions > 2006) {
                String asd;
            }


        }
        System.out.println("Total NEW input transactions (in new_input_analysis.json) -> " + totalTransactions);
    }

}
