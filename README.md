# ベルこどもクリニック予約システム

## 1. 概要

Java（Servlet / JSP）を用いて開発した小児科向け予約管理Webアプリケーションです。  
患者ログイン機能、予約登録・確認機能を実装しています。

MVCモデルを意識し、DAOパターンでデータベース処理を分離しています。

---

## 2. 使用技術

- Java 17
- Jakarta Servlet
- JSP
- Tomcat 10
- SQLite
- HTML / CSS
- Eclipse
- Git / GitHub

---

## 3. システム構成

- Model：Kanja.java
- DAO：kanjaDAO.java
- Controller：LoginServlet など
- View：JSP
- Database：SQLite（bell.db）

---

## 4. 機能一覧

- 患者ログイン機能
- 予約登録機能
- 予約確認機能
- 入力バリデーション
- セッション管理

---

## 5. 工夫した点

- ServletとDAOを分離し、責務を明確化
- nullチェックによる入力バリデーションを実装
- セッションを用いてログイン状態を管理
- 可読性を意識したパッケージ構成

---

## 6. 今後の改善点

- 管理者画面の追加
- パスワードのハッシュ化
- UIのレスポンシブ対応
- 例外処理の強化