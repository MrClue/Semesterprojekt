package org.application;

import java.util.List;

public interface IDatabaseHandler {
    public List<Program> getPrograms();
    public int getProgramID(String productionTitle);
    public boolean insertProgram(Program program);
    public boolean updateProgram();
    public boolean deleteProgram(String programtitle);

    public List<Credits> getCredits();
    public int getCredit(String programTitle, String occupation, String person);
    public boolean insertCredits(Credits credits);
    public boolean updateCredits();
    public boolean deleteCredits();
}
