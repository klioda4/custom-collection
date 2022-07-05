package ru.clevertec.kli.customcollection.list;

import java.util.Arrays;
import ru.clevertec.kli.customcollection.list.impl.CustomArrayList;

public class CollectionCheck {

    public static void main(String[] args) {
        System.out.println("__ Custom array list checks __");
        printChecksOfCustomList(new CustomArrayList<>());
    }

    private static void printChecksOfCustomList(CustomList<String> list) {
        list.add("tree");
        list.add("plant");
        list.add("nature");
        list.add(null);
        list.add("animal");
        list.add("mountain");
        list.add(null);
        printList(list);

        System.out.println("Max size check");
        list.setMaxSize(7);
        try {
            list.add("anything");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
        list.setMaxSize(0);

        System.out.println("addAll check");
        list.addAll(list);
        printList(list);

        System.out.println("Max size shrink check");
        list.setMaxSize(7);
        printList(list);
        list.setMaxSize(0);

        System.out.println("Find check");
        int treeIndex = list.find("tree");
        int animalIndex = list.find("animal");
        System.out.println("tree index is " + treeIndex);
        System.out.println("animal index is " + animalIndex);
        System.out.println();

        System.out.println("Set check");
        System.out.println("setting value " + list.set(treeIndex, "Christmas tree"));
        System.out.println("setting value " + list.set(animalIndex, "wombat"));
        printList(list);

        System.out.println("toArray check");
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println();

        System.out.println("Remove check");
        System.out.println("removing value " + list.remove(treeIndex));
        System.out.println("removing value " + list.remove(animalIndex - 1));
        printList(list);

        System.out.println("addBefore of iterator check");
        InsertableIterator<String> iterator = list.getIterator();
        while (iterator.hasNext()) {
            iterator.addBefore("before " + iterator.next());
        }
        printList(list);

        System.out.println("addAfter of iterator check");
        InsertableIterator<String> iterator2 = list.getIterator();
        while (iterator2.hasNext()) {
            iterator2.addAfter("after " + iterator2.next());
        }
        printList(list);

        System.out.println("Remove of iterator check");
        InsertableIterator<String> iterator3 = list.getIterator();
        while (iterator3.hasNext()) {
            String current = iterator3.next();
            if (current != null
                && (current.contains("before") || current.contains("after"))) {

                iterator3.remove();
            }
        }
        printList(list);

        System.out.println("Trim check");
        list.trim();
        printList(list);

        System.out.println("Clear check");
        list.clear();
        printList(list);
    }

    private static <T> void printList(CustomList<T> list) {
        System.out.println("List of " + list.size() + " items:");
        for (T item : list) {
            System.out.println(item);
        }
        System.out.println();
    }
}
