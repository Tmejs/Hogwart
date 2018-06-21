package com.tmejs.web;

import com.tmejs.db.domain.Nauczyciel;
import com.tmejs.db.service.NauczycielManagerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creanted by Tmejs on 20.06.2018.
 */
@Controller
public class AllController {

    public List<Nauczyciel> nauczycielsList;

    @RequestMapping("/all")
    public String all(Map<String, Object> model) {
        try{
            NauczycielManagerImpl impl = new NauczycielManagerImpl();
            model.put("nauczycielsList",impl.getAllNauczyciels());
        }catch (Exception e){

            Logger.getGlobal().log(Level.ALL,e.getMessage());
        }

        return "all";
    }

}
