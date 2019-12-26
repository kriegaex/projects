class Solution:
    def longestPalindrome1(self, s: str) -> str:
        longest = ''
        for i in range(len(s)):
            for j in range(len(s), i, -1):
                if len(longest) > j - i:
                    break
                elif s[i:j] == s[i:j][::-1]:
                    longest = s[i:j]
                    break
        return longest
    
    def longestPalindrome(self, s):
        print()
        start = end = 0
        for i in range(len(s)):
            len1 = self.expand(i, i, s)
            len2 = self.expand(i, i+1, s)
            leng = max(len1, len2)
            print("length", leng)
            if leng > end - start:
                if leng % 2 == 0:
                    start = int(i - (leng - 1) // 2)
                    end = int(i + leng / 2)
                else:
                    start = i - leng // 2
                    end = i + leng // 2
            print(start, end)
            print(s[start:end])
        return s[start:end + 1]
    
    def expand(self, left, right, s):
        while (left >= 0 and right < len(s) and s[left] == s[right]):
            left = left - 1
            right = right + 1
        return right - left -1

a = Solution()
print(a.longestPalindrome("cbbd"))