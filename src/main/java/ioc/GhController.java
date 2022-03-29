package ioc;

/**
 * @author think
 * @version 1.0
 * @date 2022/3/29 16:44
 */
public class GhController {
    @GhAnnotation
    private GhService ghService;

    public GhService getGhService() {
        return ghService;
    }

    public void setGhService(GhService ghService) {
        this.ghService = ghService;
    }
}
