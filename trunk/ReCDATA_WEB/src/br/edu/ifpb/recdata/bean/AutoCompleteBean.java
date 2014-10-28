package br.edu.ifpb.recdata.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="autoCompleteBean")
@ViewScoped
public class AutoCompleteBean {

    /**
     * The items currently available for selection
     */
    private List<String> items = new ArrayList<String>();

    /**
     * Current selected items
     */
    private List<String> selectedItems = new ArrayList<String>();

    /**
     * All the items available in the application
     */
    private List<String> allItems = new ArrayList<String>();

    /**
     * Create a hardcoded set of items and add all of them for selection
     */
    public AutoCompleteBean() {
        allItems.add("item1");
        allItems.add("item2");
        allItems.add("item3");
        allItems.add("item4");
        items.addAll(allItems);
    }

    /**
     * Check the current user query for selection. If it fits any of the items
     * of the system and it's not already selected, add it to the filtered List
     * 
     * @param query
     * @return
     */
    public List<String> completeItem(String query) {
        List<String> filteredList = new ArrayList<String>();
        for (String item : allItems) {
            if (item.startsWith(query) && !selectedItems.contains(item)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public List<String> getItems() {
        return items;
    }

    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

}