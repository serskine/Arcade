package mepco.ca.util;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Util {

    public static final long MS_PER_SECOND = 1000L;
    public static final long NANO_PER_MS = 1000000L;
    public static final long NANO_PER_SECOND = MS_PER_SECOND * NANO_PER_MS;

    public static <T> Optional<T> removeRandomly(List<T> items) {
        if (items.isEmpty()) {
            return Optional.empty();
        } else {
            int idx = (int) (Math.random() * items.size());
            return Optional.of(items.remove(idx));
        }
    }
    public static <T> Optional<T> chooseRandomly(List<T> items) {
        if (items.isEmpty()) {
            return Optional.empty();
        } else {
            int idx = (int) (Math.random() * items.size());
            return Optional.of(items.get(idx));
        }
    }

    public static <T> Optional<T> removeFirstMatch(final Collection<T> items, final Function<T, Boolean> isMatch) {
        final Iterator<T> iterator =  items.iterator();
        while(iterator.hasNext()) {
            T item = iterator.next();
            final boolean isMatchResult = isMatch.apply(item);
            if (isMatchResult) {
                iterator.remove();
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    /**
     * This will return al list of lists that gives all possible orderings of the provided items
     * @param items
     * @param <T>
     * @return
     */
    public static <T> Set<List<T>> allOrderingsOf(final List<T> items) {

        if (items.isEmpty()) {
            Set<List<T>> theSet = new HashSet<>();
            theSet.add(new ArrayList<>());
            return theSet;
        }

        final Set<List<T>> theSet = new HashSet<>();
        for(T item : items) {
            List<T> remainder = new ArrayList<>();
            remainder.addAll(items);
            remainder.remove(item);

            Set<List<T>> orderingsOfRemainder = allOrderingsOf(remainder);

            for(List<T> ordering : orderingsOfRemainder) {
                final List<T> newList = new ArrayList<>();
                newList.add(item);
                newList.addAll(ordering);
                theSet.add(newList);
            }
        }
        return theSet;
    }

    public static <T> Set<List<T>> allUniqueSetsOf(final List<T> items) {
        Set<List<T>> theSet = new HashSet<>();

        if (items.isEmpty()) {
            theSet.add(new ArrayList<>());
        } else {
            final List<T> front = items.subList(0, 1);
            final List<T> rear = items.subList(1, items.size());
            final Set<List<T>> rearCombinations = allUniqueSetsOf(rear);
            theSet.addAll(rearCombinations);
            for(List<T> remainder : rearCombinations) {
                List<T> toAdd = new ArrayList<>();
                toAdd.addAll(front);
                toAdd.addAll(remainder);
                theSet.add(toAdd);
            }
        }

        return theSet;
    }

    public static <T> Set<List<T>> allCombinationsOf(final List<T> items) {
        Set<List<T>> theSet = new HashSet<>();

        if (items.isEmpty()) {
            theSet.add(new ArrayList<>());
        } else {
            for(T item : items) {
                List<T> remainder = new ArrayList<>();
                remainder.addAll(items);
                remainder.remove(item);
                Set<List<T>> combos = allCombinationsOf(remainder);
                theSet.addAll(combos);
                theSet.add(remainder);
                for(List<T> remainderList : combos) {
                    final List<T> withRemainder = new ArrayList<>();
                    withRemainder.add(item);
                    withRemainder.addAll(remainderList);
                    theSet.add(withRemainder);
                }


            }
        }


        return theSet;
    }

    public static <T> String describeSet(Set<T> items) { return describeSet(items, true);   }
    public static <T> String describeSet(Set<T> items, boolean isOneLine) {
        final List<T> itemsList = items.stream().collect(Collectors.toList());
        return describeList(itemsList, isOneLine);
    }

    public static <T> String describeList(List<T> items) { return describeList(items, true);   }
    public static <T> String describeList(List<T> items, boolean isOneLine) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (!isOneLine) {
            sb.append("\n\t");
        }
        for(int i=0; i<items.size(); i++) {
            if (i>0) {
                sb.append(",");
                if (isOneLine) {
                    sb.append(" ");
                } else {
                    sb.append("\n\t");
                }
            }

            sb.append(items.get(i));
        }
        if (!isOneLine) {
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    public static final String describeObject(Object obj) {
        if (obj==null) {
            return null;
        } else {
            return obj.toString();
        }
    }

    public static <T> List<T> tournament(List<T> items, BiFunction<T, T, Boolean> runGame) {


        if (items.isEmpty()) {
            return items;
        } else if (items.size()==1) {
            return items;
        } else {

            List<T> results = new ArrayList<>();

            final List<T>[] divisions = split(2, items);

            final List<T>[] divisionResults = new List[divisions.length];
            for(int i=0; i< divisions.length; i++) {
                divisionResults[i] = tournament(divisions[i], runGame);
            }

            final List<T> divisionA = divisionResults[0];
            final List<T> divisionB = divisionResults[1];

            boolean done = divisionA.isEmpty() || divisionB.isEmpty();
            while(!done) {
                T a = divisionA.remove(0);
                T b = divisionB.remove(0);

                if (runGame.apply(a, b)) {
                    results.add(a);
                    results.add(b);
                } else {
                    results.add(b);
                    results.add(a);
                }
                done = divisionA.isEmpty() || divisionB.isEmpty();
            }

            while(!divisionA.isEmpty()) {
                results.add(divisionA.remove(0));
            }


            while(!divisionB.isEmpty()) {
                results.add(divisionB.remove(0));
            }

            return results;
        }
    }

    public static <T> List<T>[] split(int numLists, T... items) {
        return split(numLists, Arrays.asList(items));
    }

    public static <T> List<T>[] split(final int numLists, List<T> items) {
        if (items.size() < numLists) {
            throw new RuntimeException("There are not enout items in the list!");
        }

        List<T> lists[] = new List[numLists];
        for(int i=0; i<lists.length; i++) {
            lists[i] = new ArrayList<>();
        }

        for(int i=0; i<items.size(); i++) {
            lists[i%numLists].add(items.get(i));
        }

        return lists;
    }

    public static <T> Map<String, List<T>> createMappings(Collection<List<T>> combos) {
        final Map<String, List<T>> mapping = new HashMap<>();
        for(List<T> combo : combos) {
            mapping.put(describeList(combo), combo);
        }
        return mapping;
    }

    public static <T> Set<List<T>> createSetOfLists(Collection<T> items) {
        final Set<List<T>> orderSet = new HashSet<>();
        for(T item : items) {
            List<T> list = new ArrayList<>();
            list.add(item);
            orderSet.add(list);
        }
        return orderSet;
    }

    public static <T> List<T> reverse(List<T> items) {
        LinkedList<T> order = new LinkedList<>();
        for(T item : items) {
            order.offerFirst(item);
        }
        return order;
    }

    /**
     * This will check the contents of two collections to see if their contents are identical in quantity only.
     * This does not care about the ordering of the items
     * @param left
     * @param right
     * @param <T>
     * @return true if equivelent without ordering and false if either collection contained an item the other did not.
     */
    public static <T> boolean isEquivelentUnorderedItems(final Collection<T> left, final Collection<T> right) {
        final List<T> leftList = new ArrayList<>();
        final List<T> rightList = new ArrayList<>();

        leftList.addAll(left);
        rightList.addAll(right);

        if (leftList.size() != right.size()) {
            return false;   // Shortcut. The two lists must be the same size to work.
        }

        while(!leftList.isEmpty() && !rightList.isEmpty()) {
            final Optional<T> itemOpt = removeRandomly(leftList);
            if (itemOpt.isEmpty()) {
                return false;   // The right list has items the left list does not contain
            } else {
                T item = itemOpt.get();
                final boolean wasPresent = rightList.remove(item);
                if (!wasPresent) {
                    return false;   // The right list did not contain an item found in the left list
                }
            }
        }

        return true;    // There is nothing left in both lists. There are equivelent. (Without ordering)
    }

    public static <T> boolean isEquivelentUnorderedItems(
            final Collection<T> left,
            final Collection<T> right,
            final BiFunction<T, T, Boolean> isItemSame
    ) {
        final LinkedList<T> leftList = new LinkedList<>();
        final LinkedList<T> rightList = new LinkedList<>();

        leftList.addAll(left);
        rightList.addAll(right);

        if (leftList.size() != right.size()) {
            return false;   // Shortcut. The two lists must be the same size to work.
        }

        while(!leftList.isEmpty() && !rightList.isEmpty()) {
            final T leftItem = leftList.removeFirst();
            Optional<T> rightItem = removeFirstMatch(rightList, x -> isItemSame.apply(leftItem, x));
            if (!rightItem.isPresent()) {
                return false;   // There is an item the first list had the second did not.
            }
        }

        return true;    // There is nothing left in both lists. There are equivelent. (Without ordering)
    }




    public static <T> Set<T> removeDuplicates(Collection<T> items, BiFunction<T, T, Boolean> isSameFunction) {
        final Set<T> keptItems = new HashSet<>();
        for(T item : items) {
            boolean toKeep = true;
            for(T keptItem : keptItems) {
                if (isSameFunction.apply(item, keptItem)) {
                    toKeep = false;
                    break;
                }
            }
            if (toKeep) {
                keptItems.add(item);
            }
        }
        return keptItems;
    }

    public static void pause(int milliSeconds) {
        if (milliSeconds > 0) {
            try {
                Thread.sleep(milliSeconds);
            } catch (Exception e) {
                Logger.error(e);
            }
        }
    }

    public static void waitUntil(Supplier<Boolean> condition) {
        while(!condition.get()) {
            pause(500); // Pause the thread for a bit
        }
    }

    public static Color getClearerColor(final Color color) {
        return getAlphaColor(color, color.getAlpha()/2);
    }


    public static Color getAlphaColor(final Color color, double alphaP) {
        alphaP = Math.max(0, Math.min(1D, alphaP));
        final int alpha = (int) (255D * alphaP);
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        return new Color(red, green, blue, alpha);
    }

    public static int toRange(int value, int min, int max) {
        if (max==min) {
            throw new RuntimeException("The maximum value must be greater than the minimum value!");
        } else if (max<min) {
            return toRange(value, max, min);
        } else if (value < min) {
            return toRange(value + (max-min), min, max);
        } else if (value >= max) {
            return toRange(value - (max-min), min, max);
        } else {
            return value;
        }
    }

    public static double toRangeDouble(double value, double min, double max) {
        if (max==min) {
            throw new RuntimeException("The maximum value must be greater than the minimum value!");
        } else if (max<min) {
            return toRangeDouble(value, max, min);
        } else if (value < min) {
            return toRangeDouble(value + (max-min), min, max);
        } else if (value >= max) {
            return toRangeDouble(value - (max-min), min, max);
        } else {
            return value;
        }
    }

    public static Rectangle getInnerBox(Rectangle r) {
        int boxSize = Math.min(r.width, r.height);
        int centerX = r.x + r.width/2;
        int centerY = r.y + r.height/2;
        return new Rectangle(
                centerX-boxSize/2,
                centerY-boxSize/2,
                boxSize,
                boxSize
        );
    }

    public static Rectangle getOuterBox(Rectangle r) {
        int boxSize = Math.max(r.width, r.height);
        int centerX = r.x + r.width/2;
        int centerY = r.y + r.height/2;
        return new Rectangle(
                centerX-boxSize/2,
                centerY-boxSize/2,
                boxSize,
                boxSize
        );
    }

    public static final Rectangle getOuterRectangle(Rectangle r, int margin) {
        if (margin<0) {
            return getInnerRectangle(r, -margin);
        }
        return new Rectangle(
                r.x-margin,
                r.y-margin,
                r.x+r.width+margin*2,
                r.y+r.height+margin*2
        );
    }

    public static Rectangle getInnerRectangle(Rectangle r, int margin) {
        if (margin<0) {
            return getOuterRectangle(r, -margin);
        }

        return new Rectangle(
                r.x + (Math.min(margin, r.width/2)),
                r.y + (Math.min(margin, r.height/2)),
                Math.max(0, r.width-(2*margin)),
                Math.max(0, r.height-(2*margin))
        );
    }

    public static final int MARGIN = 50;

    public static final Point getCenter(Rectangle r) {
        return new Point(r.x + r.width/2, r.y + r.height/2);
    }

    public static final List<String> componentPath(Component c) {

        if (c==null) {
            return new ArrayList<>();
        } else {
            final List<String> path = componentPath(c.getParent());
            path.add(c.getName());
            return path;
        }
    }
}

