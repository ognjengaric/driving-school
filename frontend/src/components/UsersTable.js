import React from 'react';
import { DataGrid} from '@material-ui/data-grid';

const UsersTable = ({columns, data, rowNum, rowNumOptions, count, loading, pageChange, sizeChange}) => {
    return(
        <DataGrid 
            rows={data} 
            columns={columns}
            paginationMode="server"
            rowsPerPageOptions={rowNumOptions}
            pageSize={rowNum}
            onPageSizeChange={sizeChange} 
            onPageChange={pageChange}
            pagination
            rowCount={count}
            loading={loading}
        />
        
    )
} 
export default UsersTable;