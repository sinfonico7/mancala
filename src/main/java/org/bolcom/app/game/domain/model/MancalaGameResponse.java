package org.bolcom.app.game.domain.model;import lombok.Builder;import lombok.Data;import org.bolcom.app.player.model.MancalaPlayer;import org.bolcom.app.player.model.Player;import java.util.List;@Builder@Datapublic class MancalaGameResponse{    protected List<MancalaPlayer> players;    protected Integer[] table;    protected String state;}