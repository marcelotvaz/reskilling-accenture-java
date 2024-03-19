package Application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Entities.Account;
import Entities.BankAgency;
import Entities.BusinessAccount;
import Entities.Client;
import Entities.Transaction;
import Entities.enums.AccountStatus;
import Service.AccountManager;
import Service.BankManager;
import Service.IdGenerator;

public class Program {
	
	private static boolean exitProgram = false;
	private static Map<String, Object> dataBase = new HashMap<>();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws ParseException {
		Integer opcao;
		
		createSampleData();
		
        BankManager bankManager = (BankManager) dataBase.get("BankManager");
        IdGenerator idGen = (IdGenerator) dataBase.get("IdGenerator");
        AccountManager accountManager = (AccountManager) dataBase.get("AccountManager");
        do {
            System.out.println("Bem vindo! Escolha uma das opções a seguir: ");
            System.out.println("1. Acessar conta");
            System.out.println("2. Criar conta");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                	System.out.println();
                	System.out.print("Digite o número da conta: ");
                    Integer accountNumber = sc.nextInt();
                    Account acc = accountManager.getAccount(accountNumber.toString());
                    System.out.println("Bem vindo "+ acc.getClient().getName());
                    mainMenu(acc);
                    break;

                case 2:
                	int opcao2;
                    do {
                    	if(exitProgram) {
                    		break;
                    	}
                    	System.out.println();
                    	System.out.println("1. Conta Pessoa Física");
                        System.out.println("2. Conta Pessoa Jurídica");
                        System.out.println("3. Voltar");
                        System.out.print("Escolha uma opção: ");
                        opcao2 = sc.nextInt();
                    	switch(opcao2) {
	                    	case 1:
	                    		System.out.println();
	                    		System.out.print("Digite o nome completo: ");
	                            String name = sc.next();
	                            sc.nextLine();

	                            Date birthDate = null;
	                            do {
	                                System.out.print("Digite a sua data de nascimento (dd/MM/yyyy): ");
	                                String birthDateStr = sc.nextLine();
	                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	                                try {
	                                	birthDate = formatter.parse(birthDateStr);
	                                } catch (ParseException e) {
	                                    System.out.println("Data inválida. Tente novamente.");
	                                }
	                            } while (birthDate == null);
	                            
	                            String cpf;
	                            do {
	                                System.out.print("Digite o seu CPF (Sem pontuação): ");
	                                cpf = sc.nextLine();
	                                if (cpf.length() != 11) {
	                                    System.out.println("CPF inválido. O CPF deve ter exatamente 11 dígitos.");
	                                }
	                            } while (cpf.length() != 11);
	                            
	                            double limitWithdrawl;
	                            do {
	                                System.out.print("Digite o limite de transferência noturno: ");
	                                while (!sc.hasNextDouble()) {
	                                    System.out.println("Valor inválido. Tente novamente");
	                                    sc.next();
	                                }
	                                limitWithdrawl = sc.nextDouble();
	                            } while (limitWithdrawl == 0.0);
	                            
	                            Client client = new Client(name,cpf,birthDate);
	                            Account account = new Account(idGen.generateUniqueId(),client,bankManager.getBankNumber("1"),limitWithdrawl,AccountStatus.PF);
	                            accountManager.addAccount(account);
	                            System.out.println();
	                            System.out.println("Bem vindo " + client.getName() + ". O número da sua conta é: " + account.getAccountNumber());
	                            mainMenu(account);
	                    		break;
	                    	case 2:
	                    		
	                    		System.out.println();
	                    		System.out.print("Digite o nome da empresa: ");
	                            String nameCompany = sc.next();
	                            sc.nextLine();

	                            Date fundationDate = null;
	                            do {
	                                System.out.print("Digite a data de fundação da empresa (dd/MM/yyyy): ");
	                                String birthDateStr = sc.nextLine();
	                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	                                try {
	                                	fundationDate = formatter.parse(birthDateStr);
	                                } catch (ParseException e) {
	                                    System.out.println("Data inválida. Tente novamente.");
	                                }
	                            } while (fundationDate == null);
	                            
	                            String cnpj;
	                            do {
	                                System.out.print("Digite o CNPJ da empresa (Sem pontuação): ");
	                                cnpj = sc.nextLine();
	                                if (cnpj.length() != 14) {
	                                    System.out.println("CPF inválido. O CPF deve ter exatamente 11 dígitos.");
	                                }
	                            } while (cnpj.length() != 14);
	                            
	                            double limitWithdrawlCompany;
	                            do {
	                                System.out.print("Digite o limite de transferência noturno: ");
	                                while (!sc.hasNextDouble()) {
	                                    System.out.println("Valor inválido. Tente novamente");
	                                    sc.next();
	                                }
	                                limitWithdrawlCompany = sc.nextDouble();
	                            } while (limitWithdrawlCompany == 0.0);
	                            
	                            Integer score = 900;
	                            Client clientCompany = new Client(nameCompany,cnpj,fundationDate);
	                            BusinessAccount accountBusiness = new BusinessAccount(idGen.generateUniqueId(),clientCompany,bankManager.getBankNumber("1"),limitWithdrawlCompany,AccountStatus.PJ,score);
	                            accountManager.addAccount(accountBusiness);
	                            System.out.println();
	                            System.out.println("Bem vindo " + clientCompany.getName() + ". O número da sua conta é: " + accountBusiness.getAccountNumber());
	                            mainMenu(accountBusiness);
	                    		break;
	                    	case 3:
	                    		break;
	                        default:
	                            System.out.println("Opção inválida. Tente novamente.");
                    	}
                    }while (opcao2 != 3);
                    break;
                case 3:
                	sc.close();
                    System.out.println("Saindo do programa");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);

	}
	private static void createSampleData() throws ParseException {
		createSampleAgency();
		createSampleAccount();
		createIdGenerator();
	}
	private static void createSampleAgency() {
		BankManager bankManager = new BankManager();
		BankAgency bank = new BankAgency(1, "SP", "São Paulo");
		bankManager.addBank(bank);
		dataBase.put("BankManager", bankManager);

	}
	private static void createSampleAccount() throws ParseException{
		BankManager bankManagerClient = (BankManager) dataBase.get("BankManager");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatCompleted = new SimpleDateFormat("dd-MM-yyyy");
		Date now = new Date();
		Account account = new Account(123123,new Client("Pedro Silva","35212395312",formatter.parse("05/03/1983")),bankManagerClient.getBankNumber("1"),1000,AccountStatus.PF);
		account.deposit(10000);
		
		createTransaction("Deposito",formatCompleted.format(now),10000,account);
		AccountManager accountManager = new AccountManager();
		accountManager.addAccount(account);
		dataBase.put("AccountManager", accountManager);
	}
	private static void createIdGenerator() {
		IdGenerator idGen = new IdGenerator();
		dataBase.put("IdGenerator", idGen);
	}
	
	private static void createTransaction(String operation, String date, double value, Account acc) {
		Transaction tr = new Transaction(operation,date,value);
		acc.addTransaction(tr);
	}
	
	private static void mainMenu(Account account) {
		Integer accountOption;
		
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formatCompleted = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String hourFormatted = format.format(now);
        String hourFormattedCompleted = formatCompleted.format(now);
        Integer hour = Integer.parseInt(hourFormatted.substring(0, 2));
		
        do {
            System.out.println("Menu da Conta:");
            System.out.println("1. Saldo");
            System.out.println("2. Depósito");
            System.out.println("3. Saque");
            System.out.println("4. Transfêrencia");
            System.out.println("5. Extrato");
            System.out.println("6. Deslogar");
            System.out.print("Escolha uma opção: ");
            accountOption = sc.nextInt();
            sc.nextLine();

            switch (accountOption) {
                case 1:
                	System.out.println();
                    System.out.println("O seu saldo é: " + account.getBalance());
                    break;
                case 2:
                	System.out.println();
                    System.out.println("Você escolheu Depósito.");
                    System.out.print("Insira o valor: ");
                    double depositValue = sc.nextDouble();
                    account.deposit(depositValue);
                    createTransaction("Deposito",hourFormatted,depositValue,account);
                    break;
                case 3:
                	System.out.println();
                    System.out.println("Você escolheu Saque.");
                    System.out.print("Insira o valor: ");
                    double withdrawalValue = sc.nextDouble();
                	if (account.getBalance() - withdrawalValue < 0) {
                		System.out.println("Saldo insuficiente para realizar o saque. Seu saldo atual é: " + account.getBalance());
                    }
                	else {
                		account.withdrawal(withdrawalValue);
                		System.out.println("Seu saldo atual é: " + account.getBalance());
                		createTransaction("Saque",hourFormatted,-withdrawalValue,account);
                	}
                    break;
                case 4:
                	System.out.println();
                    System.out.println("Você escolheu Transferência.");
                    System.out.print("Digite a conta para a qual deseja transferir: ");
                    Integer accNumber = sc.nextInt();
                    AccountManager accountManager = (AccountManager) dataBase.get("AccountManager");
                    Account accToTransfer = accountManager.getAccount(accNumber.toString());
                    if(accToTransfer != null) {
                    	System.out.print("Digite o valor que deseja transferir: ");
                    	double value = sc.nextDouble();
                    	if(value < account.getBalance() && value <= account.getWithdrawalLimit()) {
                    	    account.withdrawal(value);
                    	    accToTransfer.deposit(value);
                    	    System.out.println("O valor de " + value + " foi transferido para a conta: " + accToTransfer.getAccountNumber());
                    	    createTransaction("Transferencia",hourFormatted,-value,account);
                    	    createTransaction("Transferencia",hourFormatted,value,accToTransfer);
                    	} else if (value > account.getWithdrawalLimit() && (hour > 20 || hour <= 8)) {
                    	    System.out.println("Valor solicitado maior que o limite noturno.");
                    	} else {
                    	    System.out.println("Saldo insuficiente ou valor solicitado maior que o limite de saque.");
                    	}
                    }
                    else {
                    	System.out.println("A conta digitada não existe");
                    }
                	break;
                case 5:
                	List<Transaction> transactions = account.getTransactions();
                	if(transactions.isEmpty()) {
                		System.out.println("Não existem transações nessa conta");
                	}
                	else {
                		String path = "c:\\temp\\extrato "+ hourFormattedCompleted +".csv";
                		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

                            writer.write("Operacao,Data,Valor\n");
                            
                            for (Transaction transation : transactions) {
                                String dataFormatada = transation.getDate();
                                writer.write(transation.getOperation() + "," +
                                             dataFormatada + "," +
                                             transation.getValue() + "\n");
                            }

                            System.out.println("Arquivo CSV criado com sucesso.");
                        } catch (IOException e) {
                            System.out.println("Erro ao criar o arquivo CSV.");
                            e.printStackTrace();
                        }
                	}
                	
                	break;
                case 6:
                	System.out.println();
                    System.out.println("Deslogando");
                    exitProgram = true;
                    System.out.println();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (accountOption != 6);
	}
}
