package forsale;

import java.util.*;

public class Strat implements Strategy {
  
  public int bid(PlayerRecord p, AuctionState a) {
    // if you dont have enough money pass
    // if card difference is less than six, drop out
    // if max card is lower than highest bid, drop out
    // if max card is higher than highest bid, bid one higher than previous bid
    int bid = 0;
    int remainingMoney = p.getCash();
    ArrayList<Card> currentCards = a.getCardsInAuction();
    int currentMax = currentCards.get(0).getQuality();
    int currentMin = currentCards.get(0).getQuality();
    
    for (int i = 1; i < currentCards.size(); i++) {
      if (currentMax < currentCards.get(i).getQuality()){
        currentMax = currentCards.get(i).getQuality();
      }
      if (currentMin > currentCards.get(i).getQuality()) {
        currentMin = currentCards.get(i).getQuality();
      }
    }
    
    if (remainingMoney < a.getCurrentBid()) {
      bid = 0;
    } else if ((currentMax - currentMin) <= 5) {
      bid = 0;
    } else if (currentMax < a.getCurrentBid()) {
      bid = 0;
    } else {
      if (p.getCash() >= 2) {
        bid = a.getCurrentBid() + 2;
      } else {
        bid = a.getCurrentBid() + 1; 
      }
    }
    return bid;
  }
  
  public Card chooseCard(PlayerRecord p, SaleState s) {
    // if highest currency card exceeds value of highest property, play highest property
    // if highest currency card is less than highest property, but still quite high, play mid range property
    // if highest currency card is between value of highest and lowest properties, play mid range property
    // if highest currency card is less than both highest and lowest properties, play lowest property
    // if the difference between cards is small, play lowest property
    ArrayList<Card> properties = p.getCards();
    ArrayList<Integer> currentCheques = s.getChequesAvailable();
    int maxCheque = currentCheques.get(0);
    int minCheque = currentCheques.get(0);
    Card card = properties.get(0);
    Card maxCard = properties.get(0);
    Card minCard = properties.get(0);
    
    for (int i = 1; i < currentCheques.size(); i++) {
      if (maxCheque < currentCheques.get(i)) {
        maxCheque = currentCheques.get(i);
      }
      if (minCheque > currentCheques.get(i)) {
        minCheque = currentCheques.get(i);
      }
    }
    
    for (int i = 1; i < properties.size(); i++) {
      if (maxCard.getQuality() < properties.get(i).getQuality()) {
        maxCard = properties.get(i);
      }
      if (minCard.getQuality() > properties.get(i).getQuality()) {
        minCard = properties.get(i);
      }
    }
    
    if (maxCheque >= maxCard.getQuality()) {
      card = maxCard;
    } else if (maxCheque < maxCard.getQuality() && (maxCard.getQuality() - maxCheque) < 4) {
      if (properties.size() > 2){
        Collections.sort(properties);
        card = properties.get(properties.size()-1);
      } else {
        card = maxCard;
      }
    } else if (maxCheque < maxCard.getQuality() && maxCheque > minCard.getQuality()) {
      if (properties.size() > 2) {
        Collections.sort(properties);
        card = properties.get(1);
      } else {
        card = minCard;
      }
    } else if (maxCheque < maxCard.getQuality() && maxCheque < minCard.getQuality()) {
      card = minCard;
    } else if (maxCard.getQuality() - minCard.getQuality() <= 5) {
      card = minCard;
    } else {
      card = minCard;
    }
    return card;
  }
}