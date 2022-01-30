/**
 * @author Malicki Cezary S22434
 */

package zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CustomersPurchaseSortFind {
    private ArrayList<Purchase> purchaseList;

    public CustomersPurchaseSortFind() {
        purchaseList = new ArrayList<>();
    }

    public void readFile(String file) {
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNext()) {
                purchaseList.add(new Purchase(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
        }
    }

    public void showSortedBy(String toSortBy) {
        ArrayList<Purchase> tempList = new ArrayList<>();
        tempList.addAll(purchaseList);
        System.out.println(toSortBy);
        switch (toSortBy) {
            case "Nazwiska":
                Comparator<Purchase> nameComparator = new Comparator<Purchase>() {
                    @Override
                    public int compare(Purchase o1, Purchase o2) {
                        if (o2.getName().compareTo(o1.getName()) < 0) {
                            return 1;
                        }
                        if (o2.getName().compareTo(o1.getName()) > 0) {
                            return -1;
                        }
                        if (o2.getName().equals(o1.getName())) {
                            if (o2.getClientID().compareTo(o1.getClientID()) < 0) {
                                return 1;
                            }
                            if (o2.getClientID().compareTo(o1.getClientID()) > 0) {
                                return -1;
                            } else return 0;
                        } else return 0;
                    }
                };
                Collections.sort(tempList, nameComparator);
                for(int i = 0; i < tempList.size(); i++) {
                    System.out.println(tempList.get(i));
                }
                break;
            case "Koszty":
                Comparator<Purchase> priceComparator = new Comparator<Purchase>() {
                    @Override
                    public int compare(Purchase o1, Purchase o2) {
                        if(o2.getCost() > o1.getCost()) {
                            return 1;
                        }
                        if(o2.getCost() < o1.getCost()) {
                            return -1;
                        }
                        if(o2.getCost() == o1.getCost()) {
                            if(o2.getClientID().compareTo(o1.getClientID()) < 0) {
                                return -1;
                            }
                            if(o2.getClientID().compareTo(o1.getClientID()) > 0) {
                                return 1;
                            } else return 0;
                        } else return 0;
                    }
                };
                Collections.sort(tempList,priceComparator);
                for(int i = 0; i < tempList.size(); i++) {
                    System.out.println(tempList.get(i) + " (koszt: " + tempList.get(i).getCost() + ")");
                }
                break;
        } System.out.println();
    }

    public void showPurchaseFor(String clientID) {
        System.out.println("Klient " + clientID);
        for(int i = 0; i < purchaseList.size(); i++) {
            if(purchaseList.get(i).getClientID().equals(clientID)) {
                System.out.println(purchaseList.get(i));
            }
        }
        System.out.println();
    }
}

