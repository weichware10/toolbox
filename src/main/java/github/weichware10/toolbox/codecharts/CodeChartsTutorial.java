package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Enums.ToolType;
import github.weichware10.util.Tutorial;
import github.weichware10.util.config.ConfigClient;

/**
 * Zeigt das Tutorial für CodeCharts an.
 */
public class CodeChartsTutorial extends Tutorial {
    /**
     * Instanziiert ein neues CodeChartsTutorial.
     *
     * @param configClient - Der Config Client für die Einstellungen.
     */
    public CodeChartsTutorial(ConfigClient configClient) {
        super(configClient, ToolType.CODECHARTS);
    }

    @Override
    protected void tutorial() {
        ;
    }
}
