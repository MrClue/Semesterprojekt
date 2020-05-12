package org.application;

import java.util.List;

public interface IDatabaseHandler {
    public List<Program> getPrograms();

    public int getProgramID(String productionTitle);

    public boolean insertProgram(Program program);

    public boolean updateProgram(int program_ID, String programTitle);

    public boolean deleteProgram(int programID); //todo: could be a void?

    public List<Credits> getCredits(); // gets all credits in database

    public List<Credits> getProgramCredits(int program_id); //todo: gets credits for the selected production

    public int getCreditID(int program_id, String occupation, String person);

    public boolean insertCredit(Credits credits);

    public boolean updateCredit(int creditID, String occupation, String person);

    public boolean deleteCredit(int programID, String occupation, String person);

    public boolean deleteCredits();
}
