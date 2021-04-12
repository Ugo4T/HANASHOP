/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import tblfood.tblFoodDTO;

/**
 *
 * @author PC
 */
public class CartObject implements Serializable{
    Map<tblFoodDTO,Integer> items ;

    public Map<tblFoodDTO, Integer> getItems() {
        return items;
    }
    
    public void addItemToCard (tblFoodDTO dto){
        
        if(this.items == null){
            this.items = new HashMap<>();
            
        }// end. if items is not exist
        int quantity = 1;
        for (tblFoodDTO food: items.keySet())
        if(food.getId().equals(dto.getId())){
            quantity = this.items.get(food) + 1;
            dto=food;
        }
        
        this.items.put(dto, quantity);
        
    }
    
    public void removeItemFromCart(String title){
        if(this.items == null){
            return;
        }
        tblFoodDTO dto = new tblFoodDTO();
        for (tblFoodDTO food: items.keySet())
        if(food.getId().equals(title)){
         //   System.out.println(food.getId());
            dto=food;
        }
        
         this.items.remove(dto);
            if(this.items.isEmpty()){
                this.items = null;
            }
    }
}