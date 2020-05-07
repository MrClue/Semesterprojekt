package org.application;

import java.util.List;

public interface IDatabaseHandler {
    public List<Program> getPrograms();
    public int getProgramID(String productionTitle);
    public boolean insertProgram(Program program);
    public boolean updateProgram();
    public boolean deleteProgram();

    public List<Credits> getCredits();
    public Credits getCredit(int id);
    public boolean insertCredit(Credits credit);
    public boolean updateCredit();
    public boolean deleteCredit();
}
