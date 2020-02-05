
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    BuddyInfoRepository repo;

    @GetMapping("/buddy")
    @ResponseBody
    public AddressBook buddies(){
        Iterable<BuddyInfo> buddies = repo.findAll();
        AddressBook book = new AddressBook();
        for (BuddyInfo bud : buddies){
            book.insert(bud);
        }
        return book;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute BuddyInfo buddy, Model model){
        model.addAttribute(buddy);
        repo.save(buddy);
        return "greeting";
    }

    @GetMapping("/create")
    public String createForm( Model model){
        model.addAttribute("buddy", new BuddyInfo());
        return "form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model){
        String buddyName = "Unknown";
        BuddyInfo bud = repo.findById(id).orElse(null);
        if (bud != null){
            buddyName = bud.getName();
        }

        model.addAttribute("name", buddyName);
        repo.deleteById(id);
        return "greeting";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("asdfsa", name);

        return "greeting";

    }

}