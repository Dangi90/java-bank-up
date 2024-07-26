package bank.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.plaf.synth.SynthOptionPaneUI;

public class BankApp {

	private static Scanner scanner = new Scanner(System.in);

	private static List<Account> accounts = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException {

		boolean run = true;
		while (run) {

			System.out.println("-----------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("-----------------------------------------");
			System.out.println("선택> ");

			int selectNo = Integer.parseInt(scanner.nextLine());

			if (selectNo == 1) {
				createAccount();
			} else if (selectNo == 2) {
				accountList();
			} else if (selectNo == 3) {
				deposit();
			} else if (selectNo == 4) {
				withdraw();
			} else if (selectNo == 5) {
				run = false; // 프로그램 종료
			} else {
				System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			}
		}
		System.out.println("프로그램 종료");

	}

	private static void createAccount() {
		System.out.println("----------------계좌생성--------------------");

		System.out.print("계좌번호: ");
		String ano = scanner.nextLine();

		System.out.print("이름: ");
		String owner = scanner.nextLine();

		int balance = 0; // 초기화
		while (true) {
			System.out.print("초기입금액: ");
			String balanceInput = scanner.nextLine();

			// 입력값이 비어있지 않은지 확인
			if (!balanceInput.isEmpty()) {
				try {
					balance = Integer.parseInt(balanceInput);
					break; // 유효한 입력이면 루프 종료
				} catch (NumberFormatException e) {
					System.out.println("유효한 숫자를 입력하세요.");
				}
			} else {
				System.out.println("입금액을 입력해야 합니다.");
			}
		}

		accounts.add(new Account(ano, owner, balance));
		System.out.println("계좌가 생성되었습니다.");
	}

	private static void accountList() {
		System.out.println("----------------계좌목록--------------------");
		if (accounts.isEmpty()) {
			System.out.println("계좌가 없습니다.");
		} else {
			for (Account account : accounts) {
				account.show(); // show() 메소드 호출
			}
		}
	}

	private static void deposit() {
		System.out.println("----------------예금--------------------");
		System.out.print("계좌번호: ");
		String ano = scanner.nextLine();

		System.out.print("예금액: ");
		int amount = Integer.parseInt(scanner.nextLine());

		Account account = findAccount(ano);
		if (account != null) {
			account.setBalance(account.getBalance() + amount);
			System.out.println("예금이 성공하였습니다..");
		} else {
			System.out.println("해당 계좌를 찾을 수 없습니다.");
		}

	}

	private static void withdraw() {
		System.out.println("----------------출금--------------------");
		System.out.print("계좌번호: ");
		String ano = scanner.nextLine();

		System.out.print("출금액: ");
		int amount = Integer.parseInt(scanner.nextLine());

		Account account = findAccount(ano);
		if (account != null) {
			if (account.getBalance() >= amount) {
				account.setBalance(account.getBalance() - amount);
				System.out.println("출금이 완료되었습니다.");
			} else {
				System.out.println("잔액이 부족합니다.");
			}
		} else {
			System.out.println("해당 계좌를 찾을 수 없습니다.");
		}
	}

	private static Account findAccount(String ano) {
		for (Account account : accounts) {
			if (account.getAno().equals(ano)) {
				return account;
			}
		}
		return null; // 계좌를 찾지 못한 경우
	}

}
