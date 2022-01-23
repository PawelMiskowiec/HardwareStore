-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 23 Sty 2022, 23:52
-- Wersja serwera: 10.4.22-MariaDB
-- Wersja PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `hardwarestore`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `torder`
--

CREATE TABLE `torder` (
  `OrderId` bigint(20) NOT NULL,
  `orderStatus` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `torder`
--

INSERT INTO `torder` (`OrderId`, `orderStatus`, `user_id`) VALUES
(1, 'NEW', 3),
(2, 'NEW', 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `torderposition`
--

CREATE TABLE `torderposition` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `torderposition`
--

INSERT INTO `torderposition` (`id`, `quantity`, `product_id`, `order_id`) VALUES
(1, 1, 1, 1),
(2, 1, 4, 1),
(3, 1, 2, 1),
(4, 1, 3, 1),
(5, 1, 2, 2),
(6, 1, 3, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tproduct`
--

CREATE TABLE `tproduct` (
  `id` bigint(20) NOT NULL,
  `brand` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `category` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `tproduct`
--

INSERT INTO `tproduct` (`id`, `brand`, `category`, `name`, `price`, `quantity`) VALUES
(1, 'Example Brand', 'PARTS', 'Zawór zwrotny 1/2\" 3/8\" 1/8\"', '24.00', 19),
(2, 'Example Brand ', 'PARTS', 'kolanko 3/8\" WZ', '5.00', 8),
(3, 'Stanley', 'TOOLS', 'Nóż z ostrzem łamanym 18mm', '28.00', 48),
(4, 'Caael', 'MATERIALS', 'płyta gumowa 30x30cm poliuretan 40ShA', '90.00', 11);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tuser`
--

CREATE TABLE `tuser` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `pass` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `tuser`
--

INSERT INTO `tuser` (`id`, `login`, `pass`) VALUES
(3, 'admin', '21232f297a57a5a743894a0e4a801fc3'),
(4, 'user', 'ee11cbb19052e40b07aac0ca060c23ee');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `torder`
--
ALTER TABLE `torder`
  ADD PRIMARY KEY (`OrderId`),
  ADD KEY `FKi0sl4qov8ir81scpposomkot4` (`user_id`);

--
-- Indeksy dla tabeli `torderposition`
--
ALTER TABLE `torderposition`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg2u8n87sdutwbdc1hvdcdd6qy` (`product_id`),
  ADD KEY `FKj462bcsf63kvrcwt4em8wg2sn` (`order_id`);

--
-- Indeksy dla tabeli `tproduct`
--
ALTER TABLE `tproduct`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `tuser`
--
ALTER TABLE `tuser`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `torder`
--
ALTER TABLE `torder`
  MODIFY `OrderId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `torderposition`
--
ALTER TABLE `torderposition`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `tproduct`
--
ALTER TABLE `tproduct`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `tuser`
--
ALTER TABLE `tuser`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
