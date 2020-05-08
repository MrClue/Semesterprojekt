package org.application;

import java.util.List;

public interface IDatabaseHandler {
    public List<Program> getPrograms();
    public int getProgramID(String productionTitle);
    public boolean insertProgram(Program program);
    public boolean updateProgram();
    public boolean deleteProgram(String programTitle); //todo: could be a void?

    public List<Credits> getCredits(); // gets all credits in database
    public List<Credits> getProductionCredits(); //todo: gets credits for the selected production
    public int getCreditID(String programTitle, String occupation, String person);
    public boolean insertCredit(Credits credits);
    public boolean updateCredit();
    public boolean deleteCredit(int programID, String occupation, String person);
    public boolean deleteCredits();
}
