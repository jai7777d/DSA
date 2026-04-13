class Solution {
public:
int n,m;
   void dfs(int row, int col, vector<vector<char>>& board, vector<vector<int>>& vis){
        vis[row][col] = 1;
        
     int delrow[] = {-1, 0, +1, 0};
        int delcol[] = {0, +1, 0, -1};
        for(int i=0;i<4;i++){
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];


                 if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m 
               && !vis[nrow][ncol] && board[nrow][ncol] == 'O'){
                dfs(nrow, ncol, board, vis);
            }
        }
   }

    void solve(vector<vector<char>>& board) {
         n = board.size();
        m = board[0].size();
         vector<vector<int>> vis(n, vector<int>(m, 0));
        
        // Step 1: boundary traversal
        
        // top & bottom row
        for(int j = 0; j < m; j++){
            if(board[0][j] == 'O' && !vis[0][j])
                dfs(0, j, board, vis);
            
            if(board[n-1][j] == 'O' && !vis[n-1][j])
                dfs(n-1, j, board, vis);
        }
        
        // left & right column
        for(int i = 0; i < n; i++){
            if(board[i][0] == 'O' && !vis[i][0])
                dfs(i, 0, board, vis);
            
            if(board[i][m-1] == 'O' && !vis[i][m-1])
                dfs(i, m-1, board, vis);
        }
        
        // Step 2: convert remaining O to X
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'O' && !vis[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    
    }
};