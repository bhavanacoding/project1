import java.io.*;
import java.util.*;
public class Main {
    public static HashMap<String, Integer>
    sortByValue(HashMap<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list
            = new LinkedList<Map.Entry<String, Integer> >(
                hm.entrySet());
 
        Collections.sort(
            list,
            (i1,
             i2) -> i1.getValue().compareTo(i2.getValue()));
 
        HashMap<String, Integer> temp
            = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    
    
    
    public static void main(String[] args) throws Exception {
        FileInputStream fis=new FileInputStream("input.txt");
        Scanner sc=new Scanner(fis);
        int number_of_employees = Integer.parseInt(sc.nextLine().split(":")[1]);
        HashMap<String,Integer> goodies_items = new HashMap<String,Integer>();
        ArrayList<Integer> list=new ArrayList<>();
        while(sc.hasNextLine())
        {
	        String current[]=sc.nextLine().split(":");
            list.add(Integer.parseInt(current[1]));
            goodies_items.put((String)current[0], Integer.parseInt(current[1]));
        }
        sc.close();
        Collections.sort(list);
        goodies_items = sortByValue(goodies_items);
	    
		int min=list.get(list.size()-1);
	    int position=0;
	    for(int i=0;i<list.size()-number_of_employees+1;i++)
	    {
	        if(list.get(i+3)-list.get(i)<min)
	        {
	            min=list.get(i+3)-list.get(i);
	            position=i;
	        }
	    }
        int j=position;
	    String a[]=new String[4]; 
	    for(int i=0;i<number_of_employees;i++)
	    {
	    for (Map.Entry mapElement : goodies_items.entrySet()) {
            String key = (String)mapElement.getKey();
  
            int value = ((int)mapElement.getValue());
            if(value==list.get(position))
            {
                a[i]=key+": "+Integer.toString(value);
                position++;
                break;
            }
            
        }
        System.out.println(a[i]);
	    }
        FileWriter fw = new FileWriter("Output.txt");
        fw.write("The goodies selected for distribution are:\n\n");
        for (int i=0;i<number_of_employees; i++) {
            fw.write(a[i]+ "\n");
        }
        
        fw.write("\nAnd the difference between the choosen goodie with highest price and the lowest price is " + (list.get(j+3)-list.get(j)));
              fw.close();
}
}
