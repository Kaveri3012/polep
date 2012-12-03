package polep.role;



@ScriptComponent
public class PolepModelRole extends AbstractRole<polepAgent> implements Role<polepAgent> {

    static Logger logger = LoggerFactory.getLogger(polepRole.class);

    @Autowired
    StuffRepository stuffRepository;

    public void act(PolepModel model){
    	
    }
    
}