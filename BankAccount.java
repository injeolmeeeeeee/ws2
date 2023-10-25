import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {
    private final String accountHolderName;
    private final String accountNumber;
    private float balance;
    private final List<String> transactions;
    private boolean closed;
    private final Date createdDate;
    private Date closedDate;

    public BankAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0f;
        this.transactions = new ArrayList<>();
        this.closed = false;
        this.createdDate = new Date();
        this.closedDate = null;
    }

    public BankAccount(String accountHolderName, float initialBalance) {
        this(accountHolderName);
        this.balance = initialBalance;
    }

    public String getAccountHolderName() {return accountHolderName;}

    public String getAccountNumber() {return accountNumber;}

    public float getBalance() {return balance;}

    public boolean isClosed() {return closed;}

    public Date getCreatedDate() {return createdDate;}

    public Date getClosedDate() {return closedDate;}

    public List<String> getTransactions() {return transactions;}

    public void deposit(float amount) {
        if (amount > 0 && !closed) {
            balance += amount;
            transactions.add("Deposit $" + amount + " at " + new Date());
        } else {
            throw new IllegalArgumentException("Invalid deposit amount or account closed.");
        }
    }

    public void withdraw(float amount) {
        if (amount > 0 && !closed && balance >= amount) {
            balance -= amount;
            transactions.add("Withdraw $" + amount + " at " + new Date());
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount or account closed.");
        }
    }

    public void closeAccount() {
        closed = true;
        closedDate = new Date();
    }

    private String generateAccountNumber() {
        // Generate a random account number (you can implement this)
        return "ACC" + (int) (Math.random() * 100000);
    }
}

public class FixedDepositAccount extends BankAccount {
    private boolean interestChanged;
    private int durationMonths;
    private float interest;

    public FixedDepositAccount(String accountHolderName, float initialBalance) {
        super(accountHolderName, initialBalance);
        this.interest = 3.0f;
        this.durationMonths = 6;
        this.interestChanged = false;
    }

    public FixedDepositAccount(String accountHolderName, float initialBalance, float interest) {
        this(accountHolderName, initialBalance);
        this.interest = interest;
        this.interestChanged = true;
    }

    public FixedDepositAccount(String accountHolderName, float initialBalance, float interest, int durationMonths) {
        this(accountHolderName, initialBalance, interest);
        this.durationMonths = durationMonths;
        this.interestChanged = true;
    }

    @Override
    public float getBalance() {
        return super.getBalance() + (interestChanged ? interest : 0);
    }

    @Override
    public void deposit(float amount) {
        // NOP (no operation) - Fixed deposit account does not allow deposits
    }

    @Override
    public void withdraw(float amount) {
        // NOP (no operation) - Fixed deposit account does not allow withdrawals
    }
}
