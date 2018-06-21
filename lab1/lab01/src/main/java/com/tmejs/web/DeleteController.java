package com.tmejs.web;

import com.tmejs.db.service.NauczycielManagerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tmejs on 20.06.2018.
 */
@Controller
public class DeleteController {

    @RequestMapping("/delete")
    public String all(Map<String, Object> model) {
        try{
            NauczycielManagerImpl impl = new NauczycielManagerImpl();
            model.put("nauczycielsList",impl.getAllNauczyciels());
        }catch (Exception e){

            Logger.getGlobal().log(Level.ALL,e.getMessage());
        }
        return "delete";
    }

}
