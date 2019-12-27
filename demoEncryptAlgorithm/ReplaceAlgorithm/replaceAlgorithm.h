#pragma once
#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
std::string encrypt(const std::vector<char> dir, std::string plain, int key);
std::string decrypt(std::vector<char> dir, std::string secret, int key);
