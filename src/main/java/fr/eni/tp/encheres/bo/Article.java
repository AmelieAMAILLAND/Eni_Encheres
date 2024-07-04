package fr.eni.tp.encheres.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Article {
	private int articleId;
	@NotBlank
	@Size(max = 30)
	private String articleName;
	@Size(max = 300)
	private String description;
	private LocalDateTime auctionStartDate;
	private LocalDateTime auctionEndDate;
	@Min(0)
	private int beginningPrice;
	@Min(0)
	private int currentPrice;
	private String state;
	private Category category;
	private PickupLocation pickupLocation;
	private List<Auction> bids;
	private User seller;
	private User buyer;

	public Article() {
		bids = new ArrayList<Auction>();
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getAuctionStartDate() {
		return auctionStartDate;
	}

	public void setAuctionStartDate(LocalDateTime auctionStartDate) {
		this.auctionStartDate = auctionStartDate;
	}

	public LocalDateTime getAuctionEndDate() {
		return auctionEndDate;
	}

	public void setAuctionEndDate(LocalDateTime auctionEndDate) {
		this.auctionEndDate = auctionEndDate;
	}
	
	public int getBeginningPrice() {
		return beginningPrice;
	}

	public void setBeginningPrice(int beginningPrice) {
		this.beginningPrice = beginningPrice;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int finalPrice) {
		this.currentPrice = finalPrice;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Auction> getBids() {
		return bids;
	}

	public void setBids(List<Auction> auctions) {
		this.bids = auctions;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public PickupLocation getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(PickupLocation pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@Override
	public String toString() {
		return String.format(
				"Article [articleId=%s, articleName=%s, description=%s, auctionStartDate=%s, auctionEndDate=%s, beginningPrice=%s, currentPrice=%s, state=%s, category=%s, pickupLocation=%s, bids=%s, seller=%s, buyer=%s]",
				articleId, articleName, description, auctionStartDate, auctionEndDate, beginningPrice, currentPrice,
				state, category, pickupLocation, bids, seller, buyer);
	}
}
