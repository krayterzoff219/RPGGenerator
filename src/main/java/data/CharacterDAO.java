package data;

import properties.PlayerCharacter;

public interface CharacterDAO {

    public int createCharacter(PlayerCharacter playerCharacter);
    public int saveCharacter(PlayerCharacter playerCharacter);

    public PlayerCharacter loadCharacter(String name);

    public int deleteCharacter(String name);
    public String displayCharacters();
}
