package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageItemsBO;
import lk.ijse.dep.app.dao.custom.ItemDAO;
import lk.ijse.dep.app.db.HibernateUtil;
import lk.ijse.dep.app.dto.ItemDTO;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ConcurrentModel;

import java.util.List;

@Component
@Transactional
public class ManageItemsBOImpl implements ManageItemsBO {

   private ItemDAO itemDAO;

   public ManageItemsBOImpl(ItemDAO itemDAO){
     this.itemDAO = itemDAO;
   }

   @Transactional(readOnly = true)
   public List<ItemDTO> getItems() throws Exception {
      return itemDAO.findAll().map(Converter::<ItemDTO>getDTOList).get();
   }

   public void createItem(ItemDTO dto) throws Exception {
     itemDAO.save(Converter.getEntity(dto));
   }

   public void updateItem(ItemDTO dto) throws Exception {
     itemDAO.update(Converter.getEntity(dto));
   }

   public void deleteItem(String itemID) throws Exception {
     itemDAO.delete(itemID);
   }

    @Override
    public ItemDTO findItem(String itemCode) throws Exception {
     return itemDAO.find(itemCode).map(Converter::<ItemDTO>getDTO).orElse(null);
    }


}
